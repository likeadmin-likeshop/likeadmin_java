package com.mdd.admin.service;

import com.mdd.admin.validate.setting.SettingSearchValidate;
import com.mdd.admin.vo.setting.SettingSearchDetailVo;

import java.util.Map;

/**
 * 热门搜索服务接口类
 */
public interface ISettingSearchService {

    /**
     * 热门搜索详情
     *
     * @author fzr
     * @return SettingSearchDetailVo
     */
    SettingSearchDetailVo detail();

    /**
     * 热门搜索新增
     *
     * @author fzr
     * @param searchValidate 参数
     */
     void save(SettingSearchValidate searchValidate);


}
