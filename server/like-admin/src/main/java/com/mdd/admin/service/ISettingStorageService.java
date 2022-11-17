package com.mdd.admin.service;

import java.util.List;
import java.util.Map;

/**
 * 存储配置服务接口类
 */
public interface ISettingStorageService {

    /**
     * 存储列表
     *
     * @author fzr
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> list();

    /**
     * 存储详情
     *
     * @author fzr
     * @param alias 引擎别名
     * @return Map<String, Object>
     */
    Map<String, Object> detail(String alias);

    /**
     * 存储编辑
     *
     * @author fzr
     * @param params 参数
     */
    void edit(Map<String, String> params);

    /**
     * 存储切换
     *
     * @author fzr
     * @param alias 引擎别名
     * @param status 状态
     */
    void change(String alias, Integer status);

}
