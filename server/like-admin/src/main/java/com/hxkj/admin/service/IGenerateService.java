package com.hxkj.admin.service;

import com.hxkj.admin.validate.PageParam;
import com.hxkj.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 代码生成接口服务类
 */
public interface IGenerateService {

    /**
     * 数据表列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<Map<String, String>>
     */
    PageResult<Map<String, String>> db(PageParam pageParam, Map<String, String> params);

}
