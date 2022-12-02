package com.mdd.front.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.entity.system.SystemLogSms;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.system.SystemLogSmsMapper;
import com.mdd.common.plugin.notice.NoticeDriver;
import com.mdd.common.plugin.notice.NoticeParams;
import com.mdd.common.util.StringUtils;
import com.mdd.common.util.ToolsUtils;
import com.mdd.front.validate.commons.SmsValidate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 短信管理
 */
@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @Resource
    SystemLogSmsMapper systemLogSmsMapper;

    /**
     * 发送短信
     *
     * @author fzr
     * @param smsValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/send")
    public AjaxResult<Object> send(@Validated @RequestBody SmsValidate smsValidate) {
        Assert.notNull(smsValidate.getMobile(), "mobile参数缺失!");
        Assert.notNull(smsValidate.getScene(), "scene参数缺失!");

        SystemLogSms systemLogSms = systemLogSmsMapper.selectOne(new QueryWrapper<SystemLogSms>()
                .eq("mobile", smsValidate.getMobile())
                .eq("scene", smsValidate.getScene())
                .in("status", Arrays.asList(0, 1))
                .orderByDesc("id")
                .last("limit 1"));

        if (StringUtils.isNotNull(systemLogSms)) {
            if (systemLogSms.getCreateTime() >= (System.currentTimeMillis() / 1000 - 60)){
                throw new OperateException("操作频繁,请稍后再试!");
            }
        }

        NoticeParams params = new NoticeParams()
                .setScene(smsValidate.getScene())
                .setMobile(smsValidate.getMobile())
                .setParams(new String[] {
                    "code:" + ToolsUtils.randomInt(4)
                });

        (new NoticeDriver()).handle(params);
        return AjaxResult.success();
    }

}
