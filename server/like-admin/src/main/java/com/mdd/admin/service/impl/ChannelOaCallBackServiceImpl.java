package com.mdd.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.admin.service.IChannelOaCallBackService;
import com.mdd.common.entity.OfficialReply;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.OfficialReplyMapper;
import com.mdd.common.plugin.wechat.WxMnpDriver;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static me.chanjar.weixin.common.api.WxConsts.EventType.SUBSCRIBE;

@Service
public class ChannelOaCallBackServiceImpl implements IChannelOaCallBackService {


    @Resource
    private OfficialReplyMapper officialReplyMapper;


    /**
     * 服务器验证
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @Override
    public String checkSignature(String signature, String timestamp, String nonce, String echostr) {
        WxMpService wxMpService = WxMnpDriver.oa();
        if (wxMpService.checkSignature(timestamp, nonce, signature)) {
            // 消息合法, 原样返回echostr
            return echostr;
        }
        return "非法请求";
    }


    /**
     * 消息回复
     *
     * @param requestBody
     * @param signature
     * @param timestamp
     * @param nonce
     * @param encType
     * @param msgSignature
     * @return
     */
    @Override
    public String post(String requestBody, String signature, String timestamp, String nonce, String encType, String msgSignature) {

        WxMpService wxMpService = WxMnpDriver.oa();
        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }

        String out = null;
        if (encType == null) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
            WxMpXmlOutMessage outMessage = this.msgHandler(inMessage);
            if (outMessage == null) {
                return "";
            }
            out = outMessage.toXml();

        } else if ("aes".equalsIgnoreCase(encType)) {
            // aes加密的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody, wxMpService.getWxMpConfigStorage(),
                    timestamp, nonce, msgSignature);

            WxMpXmlOutMessage outMessage = this.msgHandler(inMessage);
            if (outMessage == null) {
                return "";
            }
            out = outMessage.toEncryptedXml(wxMpService.getWxMpConfigStorage());
        }

        return out;
    }

    /**
     * 消息处理
     *
     * @param wxMessage
     * @return
     */
    private WxMpXmlOutMessage msgHandler(WxMpXmlMessage wxMessage) {
        try {
            // 文本消息
            if (wxMessage.getMsgType().equals(WxConsts.XmlMsgType.TEXT)) {
                String msg = keyMsg(wxMessage);
                return textBuild(msg, wxMessage);
            }

            // 事件消息
            if (wxMessage.getMsgType().equals(WxConsts.XmlMsgType.EVENT)) {
                if (wxMessage.getEvent().equals(SUBSCRIBE)) {
                    // 关注回复
                    String msg = subMsg();
                    return textBuild(msg, wxMessage);
                }
            }
        } catch (Exception e) {
            throw new OperateException("公众号回调错误" + e.getMessage());
        }
        return null;
    }

    /**
     * 返回文本消息
     *
     * @param content
     * @param wxMessage
     * @return
     */
    private WxMpXmlOutMessage textBuild(String content, WxMpXmlMessage wxMessage) {
        return WxMpXmlOutMessage.TEXT().content(content)
                .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
                .build();
    }

    /**
     * 关键词回复
     *
     * @param wxMessage
     * @return
     */
    private String keyMsg(WxMpXmlMessage wxMessage) {
        List<OfficialReply> oaReplyList = officialReplyMapper.selectList(
                new QueryWrapper<OfficialReply>()
                        .eq("reply_type", 2)
                        .eq("is_delete", 0)
                        .eq("status", 1)
                        .orderByAsc("id"));

        String msg = null;
        for (OfficialReply oaReply : oaReplyList) {
            // 全匹配
            if (oaReply.getMatchingType() == 1 && oaReply.getKeyword().equals(wxMessage.getContent())) {
                msg = oaReply.getContent();
            }
            // 模糊匹配
            if (oaReply.getMatchingType() == 2 && wxMessage.getContent().contains(oaReply.getKeyword())) {
                msg = oaReply.getContent();
            }
        }

        return msg == null ? defaultMsg() : msg;
    }

    /**
     * 默认回复
     *
     * @return
     */
    private String defaultMsg() {
        OfficialReply officialReply = officialReplyMapper.selectOne(new QueryWrapper<OfficialReply>()
                .eq("reply_type", 3)
                .eq("is_delete", 0)
                .eq("status", 1)
                .last("limit 1"));
        if (officialReply == null) {
            return null;
        }
        return officialReply.getContent();
    }

    /**
     * 关注回复内容
     *
     * @return
     */
    private String subMsg() {
        OfficialReply officialReply = officialReplyMapper.selectOne(new QueryWrapper<OfficialReply>()
                .eq("reply_type", 1)
                .eq("is_delete", 0)
                .eq("status", 1)
                .last("limit 1"));
        if (officialReply == null) {
            return defaultMsg();
        }
        return officialReply.getContent();
    }

}
