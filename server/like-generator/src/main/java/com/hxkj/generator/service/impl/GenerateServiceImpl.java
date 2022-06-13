package com.hxkj.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageHelper;
import com.hxkj.common.constant.GenConstants;
import com.hxkj.common.core.PageResult;

import com.hxkj.common.utils.StringUtil;
import com.hxkj.common.utils.TimeUtil;
import com.hxkj.generator.entity.GenTable;
import com.hxkj.generator.entity.GenTableColumn;
import com.hxkj.generator.mapper.GenTableColumnMapper;
import com.hxkj.generator.mapper.GenTableMapper;
import com.hxkj.generator.service.IGenerateService;
import com.hxkj.generator.util.GenUtil;
import com.hxkj.generator.util.VelocityUtil;
import com.hxkj.generator.validate.GenParam;
import com.hxkj.generator.validate.PageParam;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.StringWriter;
import java.util.*;

/**
 * 代码生成器服务实现类
 */
@Service
public class GenerateServiceImpl implements IGenerateService {

    @Resource
    GenTableMapper genTableMapper;

    @Resource
    GenTableColumnMapper genTableColumnMapper;

    /**
     * 库列表
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

    /**
     * 生成列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<Map<String, Object>>
     */
    @Override
    public PageResult<Map<String, Object>> genList(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<GenTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.select("id,entity_name,table_name,table_comment,create_time,update_time");

        genTableMapper.setSearch(queryWrapper, params, new String[]{
                "like:tableName@table_name:str",
                "like:tableComment@table_comment:str",
                "datetime:startTime-endTime@create_time:str"
        });

        PageHelper.startPage(page, limit);
        List<Map<String, Object>> tables = genTableMapper.selectMaps(queryWrapper);

        List<Map<String, Object>> list = new LinkedList<>();
        for (Map<String, Object> item : tables) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", item.get("id"));
            map.put("tableName", item.get("table_name"));
            map.put("entityName", item.get("entity_name"));
            map.put("tableComment", item.get("table_comment"));
            map.put("createTime", TimeUtil.timestampToDate(item.get("create_time").toString()));
            map.put("updateTime", TimeUtil.timestampToDate(item.get("update_time").toString()));
            list.add(map);
        }

        return PageResult.pageHelper(tables, list);
    }

    /**
     * 生成详情
     *
     * @author fzr
     * @return Object
     */
    @Override
    public Map<String, Object> genDetail(Integer id) {
        Map<String, Object> maps = new LinkedHashMap<>();
        GenTable genTable = genTableMapper.selectById(id);

        // 基本信息
        Map<String, Object> base = new LinkedHashMap<>();
        base.put("id", genTable.getId());
        base.put("tableName", genTable.getTableName());
        base.put("entityName", genTable.getEntityName());
        base.put("tableComment", genTable.getTableComment());
        base.put("functionAuthor", genTable.getFunctionName());
        base.put("createTime", TimeUtil.timestampToDate(genTable.getCreateTime()));
        base.put("updateTime", TimeUtil.timestampToDate(genTable.getUpdateTime()));
        maps.put("base", base);

        // 生成信息
        Map<String, Object> gen = new LinkedHashMap<>();
        gen.put("genTpl", genTable.getGenTpl());
        gen.put("genType", genTable.getGenType());
        gen.put("genPath", genTable.getGenPath());
        gen.put("moduleName", genTable.getModuleName());
        gen.put("packageName", genTable.getPackageName());
        gen.put("businessName", genTable.getBusinessName());
        gen.put("functionName", genTable.getFunctionName());
        maps.put("gen", gen);

        // 字段信息
        List<GenTableColumn> columns = genTableColumnMapper.selectList(
                new QueryWrapper<GenTableColumn>()
                        .eq("table_id", id)
                        .orderByDesc("sort"));

        maps.put("column", columns);

        return maps;
    }

    /**
     * 导入表结构
     *
     * @author fzr
     * @param tableNames 参数
     */
    @Override
    @Transactional
    public void importTable(String[] tableNames) {
        List<Map<String, String>> tables = genTableMapper.selectDbTableListByNames(tableNames);

        for (Map<String, String> map : tables) {
            // 生成表信息
            String tableName = map.get("table_name");
            String tableDesc = map.get("table_comment");
            GenTable table = new GenTable();
            table.setTableName(tableName);
            table.setTableComment(tableDesc);
            table.setEntityName(GenUtil.toClassName(tableName));
            table.setPackageName("com.hxkj.admin");
            table.setModuleName(GenUtil.toModuleName("com.hxkj.admin"));
            table.setBusinessName(GenUtil.toBusinessName(tableName));
            table.setFunctionName(GenUtil.replaceText(tableDesc));
            table.setFunctionAuthor("likeAdmin");
            table.setCreateTime(System.currentTimeMillis() / 1000);
            table.setUpdateTime(System.currentTimeMillis() / 1000);
            int row = genTableMapper.insert(table);

            // 生成列信息
            if (row > 0) {
                List<GenTableColumn> genTableColumns = genTableMapper.selectDbTableColumnsByName(tableName);
                for (GenTableColumn column : genTableColumns) {
                    String columnType = GenUtil.getDbType(column.getColumnType());
                    String columnName = column.getColumnName();

                    column.setTableId(table.getId());
                    column.setUpdateTime(table.getUpdateTime());
                    column.setCreateTime(table.getCreateTime());
                    column.setJavaField(StringUtil.toCamelCase(columnName));
                    column.setJavaType("String");
                    column.setQueryType("EQ");
                    column.setIsInsert(GenConstants.REQUIRE);

                    if (GenUtil.isArraysContains(GenConstants.COLUMN_TYPE_STR, columnType) ||
                        GenUtil.isArraysContains(GenConstants.COLUMN_TYPE_TEXT, columnType)) {
                        Integer columnLength = GenUtil.getColumnLength(column.getColumnType());
                        String htmlType = columnLength >= 500 || GenUtil.isArraysContains(GenConstants.COLUMN_TYPE_TEXT, columnType)
                                ? GenConstants.HTML_TEXTAREA
                                : GenConstants.HTML_INPUT;
                        column.setHtmlType(htmlType);
                    }

                    else if (GenUtil.isArraysContains(GenConstants.COLUMN_TYPE_TIME, columnType)) {
                        column.setJavaType(GenConstants.TYPE_DATE);
                        column.setHtmlType(GenConstants.HTML_DATETIME);
                    }

                    else if (GenUtil.isArraysContains(GenConstants.COLUMN_TYPE_NUMBER, columnType)) {
                        column.setHtmlType(GenConstants.HTML_INPUT);
                        String[] str = StringUtil.split(StringUtil.substringBetween(column.getColumnType(), "(", ")"), ",");
                        if (str != null && str.length == 2 && Integer.parseInt(str[1]) > 0) {
                            column.setJavaType(GenConstants.TYPE_BIG_DECIMAL); // 浮点形
                        } else if (str != null && str.length == 1 && Integer.parseInt(str[0]) <= 10) {
                            column.setJavaType(GenConstants.TYPE_INTEGER);     // 整数形
                        } else {
                            column.setJavaType(GenConstants.TYPE_LONG);        // 长整形
                        }
                    }

                    // 编辑字段
                    if (!GenUtil.isArraysContains(GenConstants.COLUMN_NAME_NOT_EDIT, columnName) && column.getIsPk() == 0) {
                        column.setIsEdit(GenConstants.REQUIRE);
                    }

                    // 列表字段
                    if (!GenUtil.isArraysContains(GenConstants.COLUMN_NAME_NOT_LIST, columnName) && column.getIsPk() == 0) {
                        column.setIsList(GenConstants.REQUIRE);
                    }

                    // 查询字段
                    if (!GenUtil.isArraysContains(GenConstants.COLUMN_NAME_NOT_QUERY, columnName) && column.getIsPk() == 0) {
                        column.setIsQuery(GenConstants.REQUIRE);
                    }

                    // 查询字段类型
                    if (StringUtil.endsWithIgnoreCase(columnName, "name")) {
                        column.setQueryType(GenConstants.QUERY_LIKE);
                    }

                    // 根据字段设置
                    if (StringUtil.endsWithIgnoreCase(columnName, "status")) {
                        // 状态字段设置单选框
                        column.setHtmlType(GenConstants.HTML_RADIO);
                    } else if (StringUtil.endsWithIgnoreCase(columnName, "type") ||
                               StringUtil.endsWithIgnoreCase(columnName, "sex")) {
                        // 类型&性别字段设置下拉框
                        column.setHtmlType(GenConstants.HTML_SELECT);
                    } else if (StringUtil.endsWithIgnoreCase(columnName, "image")) {
                        // 图片字段设置图片上传控件
                        column.setHtmlType(GenConstants.HTML_IMAGE_UPLOAD);
                    } else if (StringUtil.endsWithIgnoreCase(columnName, "file")) {
                        // 文件字段设置文件上传控件
                        column.setHtmlType(GenConstants.HTML_FILE_UPLOAD);
                    } else if (StringUtil.endsWithIgnoreCase(columnName, "content")) {
                        // 内容字段设置富文本控件
                        column.setHtmlType(GenConstants.HTML_EDITOR);
                    }

                    genTableColumnMapper.insert(column);
                }
            }
        }
    }

    /**
     * 编辑表结构
     *
     * @author fzr
     * @param genParam 参数
     */
    @Override
    @Transactional
    public void editTable(GenParam genParam) {
        GenTable model = genTableMapper.selectById(genParam.getId());
        Assert.notNull(model, "数据已丢失");

        model.setTableName(genParam.getTableName());
        model.setEntityName(genParam.getEntityName());
        model.setTableComment(genParam.getTableComment());
        model.setFunctionAuthor(genParam.getFunctionAuthor());
        model.setRemarks(genParam.getRemarks());
        model.setGenTpl(genParam.getGenTpl());
        model.setModuleName(genParam.getModuleName());
        model.setPackageName(genParam.getPackageName());
        model.setBusinessName(genParam.getBusinessName());
        model.setFunctionName(genParam.getFunctionName());
        model.setGenType(genParam.getGenType());
        model.setGenPath(genParam.getGenPath());
        genTableMapper.updateById(model);

        for (Map<String, String> item : genParam.getColumns()) {
            Integer id = Integer.parseInt(item.get("id"));
            GenTableColumn column = genTableColumnMapper.selectById(id);
            column.setColumnComment(item.get("columnComment"));
            column.setJavaField(item.get("javaField"));
            column.setIsPk(Integer.parseInt(item.get("isPK")));
            column.setIsIncrement(Integer.parseInt(item.get("isIncrement")));
            column.setIsRequired(Integer.parseInt(item.get("isRequired")));
            column.setIsInsert(Integer.parseInt(item.get("isInsert")));
            column.setIsEdit(Integer.parseInt(item.get("isEdit")));
            column.setIsList(Integer.parseInt(item.get("isList")));
            column.setIsQuery(Integer.parseInt(item.get("isQuery")));
            column.setQueryType(item.get("queryType"));
            column.setHtmlType(item.get("htmlType"));
            column.setDictType(item.get("dictType"));
            genTableColumnMapper.updateById(column);
        }
    }

    /**
     * 删除表结构
     *
     * @author fzr
     * @param id 主键
     */
    @Override
    @Transactional
    public void deleteTable(Integer id) {
        GenTable genTable = genTableMapper.selectById(id);
        Assert.notNull(genTable, "数据已丢失");

        genTableMapper.deleteById(id);
        genTableColumnMapper.delete(new QueryWrapper<GenTableColumn>().eq("table_id", id));
    }

    /**
     * 同步数据表
     *
     * @author fzr
     */
    @Override
    public void syncTable(Integer id) {

    }

    /**
     * 预览代码
     *
     * @author fzr
     * @return Map<String, String>
     */
    @Override
    public Map<String, String> previewCode(Integer id) {

        GenTable table = genTableMapper.selectById(id);

        // 初始模板
        VelocityUtil.initVelocity();
        VelocityContext context = VelocityUtil.prepareContext(table);

        // 渲染模板
        Map<String, String> map = new LinkedHashMap<>();
        List<String> templates = VelocityUtil.getTemplateList("curd");
        for (String template : templates) {
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);
            map.put(template, sw.toString());
        }

        return map;
    }

    /**
     * 生成代码
     *
     * @author fzr
     * @return Object
     */
    @Override
    public Object genCode(Integer id) {
        return null;
    }

}
