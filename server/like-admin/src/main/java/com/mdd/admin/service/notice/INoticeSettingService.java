package com.mdd.admin.service.notice;

import com.mdd.admin.vo.notice.NoticeSettingDetailVo;
import com.mdd.admin.vo.notice.NoticeSettingListVo;

import java.util.List;
import java.util.Map;

/**
 * 通知设置服务接口类
 */
public interface INoticeSettingService {

    /**
     * 通知设置列表
     *
     * @author fzr
     * @return List<NoticeSettingListVo>
     */
    List<NoticeSettingListVo> list();

    NoticeSettingDetailVo detail(Integer id);

    void save(Map<String, Object> params);

}
