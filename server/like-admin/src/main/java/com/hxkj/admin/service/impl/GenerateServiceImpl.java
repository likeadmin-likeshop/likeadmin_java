package com.hxkj.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.hxkj.admin.service.IGenerateService;
import com.hxkj.admin.validate.PageParam;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.mapper.generate.GenTableMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class GenerateServiceImpl implements IGenerateService {

    @Resource
    GenTableMapper genTableMapper;

    @Override
    public List<Map<String, String>> db(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        PageHelper.startPage(page, limit);
        List<Map<String, String>> tables = genTableMapper.selectDbTableList();

        List<Map<String, String>> list = new LinkedList<>();
        for (Map<String, String> item : tables) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("tableName", item.get("table_name"));
            map.put("tableComment", item.get("table_comment"));
            map.put("createTime", item.get("create_time"));
            map.put("updateTime", item.getOrDefault("update_time", ""));
            list.add(map);
        }
        System.out.println(PageResult.pageHelper(tables));
        return list;
    }

}
