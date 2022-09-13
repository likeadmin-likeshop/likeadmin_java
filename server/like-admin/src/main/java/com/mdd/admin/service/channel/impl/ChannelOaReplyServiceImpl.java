package com.mdd.admin.service.channel.impl;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.admin.service.channel.IChannelOaReplyService;
import com.mdd.common.entity.OfficialReply;
import com.mdd.common.entity.server.Sys;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.OfficialReplyMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

@Service
public class ChannelOaReplyServiceImpl implements IChannelOaReplyService {

    @Resource
    OfficialReplyMapper officialReplyMapper;


    @Override
    public Object list() {
        return null;
    }

    @Override
    public Object detail() {
        return null;
    }

    @Override
    public void add(Map<String, String> params) {
        String type = params.getOrDefault("type", "");
        OfficialReply officialReply = new OfficialReply();
        switch (type) {
            case "follow":
                Assert.notNull(params.get("name"), "规则名称不能为空");
                Assert.notNull(params.get("contentType"), "请正确选择回复类型");
                Assert.notNull(params.get("content"), "回复内容不能为空");
                Assert.notNull(params.get("status"), "请正确选择状态");
                officialReply.setName(params.get("name"));
                officialReply.setReplyType(1);
                officialReply.setContentType(Integer.parseInt(params.get("replyType")));
                officialReply.setContent(params.get("content"));
                officialReply.setStatus(Integer.parseInt(params.get("status")));
                officialReply.setCreateTime(System.currentTimeMillis() / 1000);
                officialReply.setUpdateTime(System.currentTimeMillis() / 1000);
                officialReplyMapper.insert(officialReply);
                break;
            case "keyword":
                Assert.notNull(params.get("name"), "规则名称不能为空");
                Assert.notNull(params.get("keyword"), "关键词不能为空");
                Assert.notNull(params.get("matchingType"), "请正确选择匹配模式");
                Assert.notNull(params.get("contentType"), "请正确选择回复类型");
                Assert.notNull(params.get("content"), "回复内容不能为空");
                Assert.notNull(params.get("status"), "请正确选择状态");
                officialReply.setName(params.get("name"));
                officialReply.setKeyword(params.get("keyword"));
                officialReply.setMatchingType(Integer.parseInt(params.get("matchingType")));
                officialReply.setReplyType(2);
                officialReply.setContent(params.get("content"));
                officialReply.setContentType(Integer.parseInt(params.get("contentType")));
                officialReply.setStatus(Integer.parseInt(params.get("status")));
                officialReply.setCreateTime(System.currentTimeMillis() / 1000);
                officialReply.setUpdateTime(System.currentTimeMillis() / 1000);
                officialReplyMapper.insert(officialReply);
                break;
            case "default":
                Assert.notNull(params.get("name"), "规则名称不能为空");
                Assert.notNull(params.get("contentType"), "请正确选择回复类型");
                Assert.notNull(params.get("content"), "回复内容不能为空");
                Assert.notNull(params.get("status"), "请正确选择状态");
                officialReply.setName(params.get("name"));
                officialReply.setReplyType(3);
                officialReply.setMatchingType(Integer.parseInt(params.get("matchingType")));
                officialReply.setStatus(Integer.parseInt(params.get("status")));
                officialReply.setCreateTime(System.currentTimeMillis() / 1000);
                officialReply.setUpdateTime(System.currentTimeMillis() / 1000);
                officialReplyMapper.insert(officialReply);
                break;
            default:
                throw new OperateException("不被支持的类型");
        }

    }

    @Override
    public void edit() {

    }

    @Override
    public void del() {

    }

}
