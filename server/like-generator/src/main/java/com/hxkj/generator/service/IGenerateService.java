package com.hxkj.generator.service;

import com.hxkj.common.core.PageResult;
import com.hxkj.generator.validate.PageParam;

import java.util.Map;

/**
 * 代码生成接口服务类
 */
public interface IGenerateService {

    /**
     * 表列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<Map<String, String>>
     */
    PageResult<Map<String, String>> db(PageParam pageParam, Map<String, String> params);

    /**
     * 导入表
     *
     * @author fzr
     * @param tableNames 参数
     */
    void importTable(String[] tableNames);

    Object previewCode();

}
