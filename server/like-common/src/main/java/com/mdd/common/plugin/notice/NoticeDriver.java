package com.mdd.common.plugin.notice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.common.entity.notice.NoticeSetting;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.notice.NoticeSettingMapper;
import com.mdd.common.plugin.notice.engine.SmsNoticeHandle;
import com.mdd.common.plugin.notice.template.SmsTemplate;
import com.mdd.common.plugin.notice.vo.NoticeSmsVo;
import com.mdd.common.util.SpringUtils;
import com.mdd.common.util.StringUtils;

/**
 * 通知驱动
 */
public class NoticeDriver {

    public static void handle(NoticeSmsVo noticeSmsVo) {
        // 场景模板
        NoticeSettingMapper noticeSettingMapper = SpringUtils.getBean(NoticeSettingMapper.class);
        NoticeSetting noticeSetting = noticeSettingMapper.selectOne(
                new QueryWrapper<NoticeSetting>()
                        .eq("scene", noticeSmsVo.getScene())
                        .eq("is_delete", 0)
                        .last("limit 1"));

        if (StringUtils.isNull(noticeSetting)) {
            throw new OperateException("消息场景不存在!");
        }

        // 短信通知
        SmsTemplate smsTemplate = new SmsTemplate();
        smsTemplate.setName(noticeSetting.getName());
        smsTemplate.setType(noticeSetting.getType());
        smsTemplate.setParams(noticeSetting.getSmsNotice());
        if (StringUtils.isNotNull(smsTemplate.getStatus()) && smsTemplate.getStatus().equals(1)) {
            (new SmsNoticeHandle()).send(noticeSmsVo, smsTemplate);
        }
    }

}
