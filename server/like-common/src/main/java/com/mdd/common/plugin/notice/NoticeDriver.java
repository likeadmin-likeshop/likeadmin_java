package com.mdd.common.plugin.notice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.common.entity.notice.NoticeSetting;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.notice.NoticeSettingMapper;
import com.mdd.common.plugin.notice.engine.MpNotice;
import com.mdd.common.plugin.notice.engine.OaNotice;
import com.mdd.common.plugin.notice.engine.SmsNotice;
import com.mdd.common.utils.SpringUtil;
import com.mdd.common.utils.StringUtil;
import com.mdd.common.utils.ToolsUtil;

import java.util.Map;

public class NoticeDriver {

    public void handle(Map<String, String> config, Map<String, String> params) {
        // 获取通知场景
        if (StringUtil.isNull(config.get("scene"))) {
            throw new OperateException("scene参数缺失!");
        }

        // 获取场景模板
        NoticeSettingMapper noticeSettingMapper = SpringUtil.getBean(NoticeSettingMapper.class);
        NoticeSetting noticeSetting = noticeSettingMapper.selectOne(
                new QueryWrapper<NoticeSetting>()
                    .eq("scene", Integer.parseInt(config.get("scene")))
                    .eq("is_delete", 0)
                    .last("limit 1"));

        if (StringUtil.isNull(noticeSetting)) {
            throw new OperateException("消息场景不存在!");
        }

        // 短信通知
        Map<String, String> smsTemplate = ToolsUtil.jsonToMap(noticeSetting.getSmsNotice());
        if (StringUtil.isNotEmpty(smsTemplate.get("status")) && Integer.parseInt(smsTemplate.get("status")) == 1) {
            (new SmsNotice()).send(config, params, smsTemplate);
        }

        // 小程序订阅通知
        Map<String, String> mnpTemplate = ToolsUtil.jsonToMap(noticeSetting.getMnpNotice());
        if (StringUtil.isNotEmpty(mnpTemplate.get("status")) && Integer.parseInt(mnpTemplate.get("status")) == 1) {
            (new MpNotice()).send(config, params, mnpTemplate);
        }

        // 公众号订阅通知
//        Map<String, String> oaTemplate = ToolsUtil.jsonToMap(noticeSetting.getOaNotice());
//        if (StringUtil.isNotEmpty(oaTemplate.get("status")) && Integer.parseInt(oaTemplate.get("status")) == 1) {
//            (new OaNotice()).send(config, params, oaTemplate);
//        }

    }

}
