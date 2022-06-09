package com.hxkj.admin.service;

import com.hxkj.admin.validate.PageParam;

import java.util.List;
import java.util.Map;

/**
 * 代码生成接口服务类
 */
public interface IGenerateService {

    List<Map<String, String>> db(PageParam pageParam, Map<String, String> params);

}
