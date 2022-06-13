package com.hxkj.generator.service;

import com.hxkj.common.core.PageResult;
import com.hxkj.generator.validate.PageParam;

import java.util.List;
import java.util.Map;

/**
 * 代码生成接口服务类
 */
public interface IGenerateService {

    /**
     * 库列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<Map<String, String>>
     */
    PageResult<Map<String, String>> db(PageParam pageParam, Map<String, String> params);

    /**
     * 生成列表
     *
     * @author fzr
     * @return Object
     */
    PageResult<Map<String, Object>> genList(PageParam pageParam, Map<String, String> params);

    /**
     * 生成详情
     *
     * @author fzr
     * @return Object
     */
    Map<String, Object> genDetail(Integer id);

    /**
     * 导入表结构
     *
     * @author fzr
     * @param tableNames 参数
     */
    void importTable(String[] tableNames);

    /**
     * 编辑表结构
     *
     * @author fzr
     */
    void editTable(Integer id);

    /**
     * 删除表结构
     *
     * @author fzr
     */
    void deleteTable(Integer id);

    /**
     * 同步数据表
     *
     * @author fzr
     */
    void syncTable(Integer id);

    /**
     * 预览代码
     *
     * @author fzr
     * @return Map<String, String>
     */
    Map<String, String> previewCode(Integer id);

    /**
     * 生成代码
     *
     * @author fzr
     * @return Object
     */
    Object genCode(Integer id);

}
