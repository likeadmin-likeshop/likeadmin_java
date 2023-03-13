package com.mdd.common.plugin.notice.engine;

import com.mdd.common.entity.notice.NoticeRecord;
import com.mdd.common.enums.NoticeEnum;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.notice.NoticeRecordMapper;
import com.mdd.common.plugin.notice.vo.NoticeSmsVo;
import com.mdd.common.plugin.notice.template.SmsTemplate;
import com.mdd.common.plugin.sms.SmsDriver;
import com.mdd.common.util.ConfigUtils;
import com.mdd.common.util.SpringUtils;
import com.mdd.common.util.StringUtils;

import java.util.*;

/**
 * 短信通知
 */
public class SmsNoticeHandle {

    /**
     * 发送短信通知
     *
     * @author fzr
     * @param noticeSmsVo  基础配置
     * @param smsTemplate   短信模板
     */
    public void send(NoticeSmsVo noticeSmsVo, SmsTemplate smsTemplate) {
        // 基础参数
        String mobile = noticeSmsVo.getMobile();
        Integer scene = noticeSmsVo.getScene();

        // 模板参数
        Map<String, String> params = new LinkedHashMap<>();
        if (StringUtils.isNotNull(noticeSmsVo.getParams())) {
            for (String s : noticeSmsVo.getParams()) {
                String[] arr =  s.split(":");
                String key = arr[0].trim();
                String val = arr[1].trim();
                params.put(key, val);
            }
        }

        // 消息记录
        NoticeRecordMapper noticeRecordMapper = SpringUtils.getBean(NoticeRecordMapper.class);
        int expire = StringUtils.isNull(noticeSmsVo.getExpire()) ? 0 : noticeSmsVo.getExpire();
        NoticeRecord noticeRecord = new NoticeRecord();
        noticeRecord.setScene(scene);
        noticeRecord.setUserId(0);
        noticeRecord.setAccount(mobile);
        noticeRecord.setTitle(smsTemplate.getName());
        noticeRecord.setCode(params.getOrDefault("code", ""));
        noticeRecord.setContent(this.getContent(params, smsTemplate.getContent()));
        noticeRecord.setReceiver(NoticeEnum.SENDER_SMS.getCode());
        noticeRecord.setStatus(NoticeEnum.STATUS_WAIT.getCode());
        noticeRecord.setIsRead(NoticeEnum.VIEW_UNREAD.getCode());
        noticeRecord.setIsCaptcha(smsTemplate.getType().equals(2) ? 1 : 0);
        noticeRecord.setExpireTime(expire + (System.currentTimeMillis() / 1000));
        noticeRecord.setCreateTime(System.currentTimeMillis() / 1000);
        noticeRecord.setUpdateTime(System.currentTimeMillis() / 1000);
        noticeRecordMapper.insert(noticeRecord);

        // 消息发送
        if (StringUtils.isNotEmpty(mobile)) {
            try {
                (new SmsDriver())
                        .setMobile(mobile)
                        .setTemplateCode(smsTemplate.getTemplateId())
                        .setTemplateParam(this.getSmsParams(params, smsTemplate.getContent()))
                        .sendSms();

                noticeRecord.setStatus(NoticeEnum.STATUS_OK.getCode());
                noticeRecord.setUpdateTime(System.currentTimeMillis() / 1000);
                noticeRecordMapper.updateById(noticeRecord);
            } catch (OperateException e) {
                noticeRecord.setError(e.getMsg());
                noticeRecord.setStatus(NoticeEnum.STATUS_FAIL.getCode());
                noticeRecord.setUpdateTime(System.currentTimeMillis() / 1000);
                noticeRecordMapper.updateById(noticeRecord);
            }
        }
    }

    /**
     * 获取短信内容
     *
     * @author fzr
     * @param params 短信参数
     * @param content 短信模板
     * @return String 短信内容
     */
    private String getContent(Map<String, String> params, String content) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String searchReplace = "\\$\\{" + entry.getKey() + "}";
            content = content.replaceAll(searchReplace, entry.getValue());
        }

        return content;
    }

    /**
     * 腾讯云参数处理
     *
     * @author fzr
     * @param params 短信参数
     * @param content 短信内容
     * @return Map<String, String>
     */
    private Map<String, String> getSmsParams(Map<String, String> params, String content) {
        String engine = ConfigUtils.get("sms", "default", "");
        if (!engine.equals("tencent")) {
            return params;
        }

        // 获取内容变量
        List<String> arr = new LinkedList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String search = "\\$\\{" + entry.getKey() + "}";
           if (content.indexOf(search) != 1 && !arr.contains(entry.getKey())) {
               arr.add(entry.getKey());
           }
        }

        // 获取变量名称
        List<Integer> arrIndex = new LinkedList<>();
        Map<Integer, String> arr2 = new LinkedHashMap<>();
        if (arr.size() > 0) {
            for (String v: arr) {
                int k = content.indexOf(v);
                arrIndex.add(k);
                arr2.put(k, v);
            }
        }

        // 从小到大排序
        List<String> arr3 = new LinkedList<>();
        Collections.sort(arrIndex);
        for (Integer i : arrIndex) {
            arr3.add(arr2.get(i));
        }

        // 取变量对应值
        Map<String, String> arr4 = new LinkedHashMap<>();
        for (String v : arr3) {
            if (StringUtils.isNotNull(params.get(v))) {
                arr4.put(params.get(v), "");
            }
        }

        return arr4;
    }

}
