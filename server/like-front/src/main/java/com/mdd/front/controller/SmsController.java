package com.mdd.front.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.entity.notice.NoticeRecord;
import com.mdd.common.enums.NoticeEnum;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.notice.NoticeRecordMapper;
import com.mdd.common.plugin.notice.NoticeDriver;
import com.mdd.common.plugin.notice.vo.NoticeSmsVo;
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
    NoticeRecordMapper noticeRecordMapper;

    /**
     * 发送短信
     *
     * @author fzr
     * @param smsValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/send")
    public AjaxResult<Object> send(@Validated @RequestBody SmsValidate smsValidate) {
        NoticeRecord noticeRecord = noticeRecordMapper.selectOne(new QueryWrapper<NoticeRecord>()
                .eq("mobile", smsValidate.getMobile())
                .eq("scene", smsValidate.getScene())
                .eq("status", Arrays.asList(NoticeEnum.STATUS_WAIT, NoticeEnum.STATUS_OK))
                .orderByDesc("id")
                .last("limit 1"));

        if (StringUtils.isNotNull(noticeRecord)) {
            if (noticeRecord.getCreateTime() >= (System.currentTimeMillis() / 1000 - 60)){
                throw new OperateException("操作频繁,请稍后再试!");
            }
        }

        NoticeSmsVo params = new NoticeSmsVo()
                .setScene(smsValidate.getScene())
                .setMobile(smsValidate.getMobile())
                .setExpire(900)
                .setParams(new String[] {
                    "code:" + ToolsUtils.randomInt(4)
                });

        NoticeDriver.handle(params);
        return AjaxResult.success();
    }

}
