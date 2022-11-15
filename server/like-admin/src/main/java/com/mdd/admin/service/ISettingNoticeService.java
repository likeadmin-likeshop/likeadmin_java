package com.mdd.admin.service;

import com.mdd.admin.vo.setting.SettingNoticeDetailVo;
import com.mdd.admin.vo.setting.SettingNoticeListedVo;

import java.util.List;
import java.util.Map;

/**
 * 通知设置服务接口类
 */
public interface ISettingNoticeService {

    /**
     * 通知设置列表
     *
     * @author fzr
     * @param recipient 1=用户, 2=平台
     * @return List<NoticeSettingListVo>
     */
    List<SettingNoticeListedVo> list(Integer recipient);

    /**
     * 通知设置详情
     *
     * @author fzr
     * @param id 主键
     * @return NoticeDetailVo
     */
    SettingNoticeDetailVo detail(Integer id);

    /**
     * 通知设置保存
     *
     * @author fzr
     * @param params 参数
     */
    void save(Map<String, Object> params);

}
