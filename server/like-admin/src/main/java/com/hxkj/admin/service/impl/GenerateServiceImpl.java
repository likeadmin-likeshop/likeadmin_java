package com.hxkj.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.hxkj.admin.service.IGenerateService;
import com.hxkj.admin.validate.PageParam;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.mapper.generate.GenTableMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 代码生成器服务实现类
 */
@Service
public class GenerateServiceImpl implements IGenerateService {

    @Resource
    GenTableMapper genTableMapper;

    /**
     * 数据表列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<Map<String, String>>
     */
    @Override
    public PageResult<Map<String, String>> db(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        PageHelper.startPage(page, limit);
        List<Map<String, String>> tables = genTableMapper.selectDbTableList(params);

        List<Map<String, String>> list = new LinkedList<>();
        for (Map<String, String> item : tables) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("tableName", item.get("table_name"));
            map.put("tableComment", item.get("table_comment"));
            map.put("createTime", item.get("create_time"));
            map.put("updateTime", item.getOrDefault("update_time", ""));
            list.add(map);
        }

        return PageResult.pageHelper(tables, list);
    }

}
