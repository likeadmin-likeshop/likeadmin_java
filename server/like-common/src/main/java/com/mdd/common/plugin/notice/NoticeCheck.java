package com.mdd.common.plugin.notice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.common.entity.notice.NoticeRecord;
import com.mdd.common.entity.server.Sys;
import com.mdd.common.mapper.notice.NoticeRecordMapper;
import com.mdd.common.util.SpringUtils;
import com.mdd.common.util.StringUtils;

import javax.annotation.Resource;

/**
 * 通知验证码验证器
 */
public class NoticeCheck {

    public static Boolean verify(Integer scene, Object code) {
        NoticeRecordMapper noticeRecordMapper = SpringUtils.getBean(NoticeRecordMapper.class);

        NoticeRecord noticeRecord = noticeRecordMapper.selectOne(new QueryWrapper<NoticeRecord>()
                .eq("scene", scene)
                .eq("status", 1)
                .eq("is_read", 0)
                .eq("is_captcha", 1)
                .eq("is_delete", 0)
                .eq("code", code.toString().toLowerCase())
                .last("limit 1"));

        if (StringUtils.isNull(noticeRecord)) {
            return false;
        }

        boolean result = noticeRecord.getExpireTime() > (System.currentTimeMillis() / 1000);

        noticeRecord.setIsRead(1);
        noticeRecord.setUpdateTime(System.currentTimeMillis() / 1000);
        noticeRecordMapper.updateById(noticeRecord);
        return result;
    }

}
