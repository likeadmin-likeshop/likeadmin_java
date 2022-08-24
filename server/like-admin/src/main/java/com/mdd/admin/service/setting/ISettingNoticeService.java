package com.mdd.admin.service.setting;

import com.mdd.admin.vo.setting.NoticeDetailVo;
import com.mdd.admin.vo.setting.NoticeListVo;

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
     * @return List<NoticeSettingListVo>
     */
    List<NoticeListVo> list();

    /**
     * 通知设置详情
     *
     * @author fzr
     * @param id 主键
     * @return NoticeDetailVo
     */
    NoticeDetailVo detail(Integer id);

    /**
     * 通知设置保存
     *
     * @author fzr
     * @param params 参数
     */
    void save(Map<String, Object> params);

}
