package com.hxkj.generator.service.impl;

import com.github.pagehelper.PageHelper;
import com.hxkj.common.constant.GenConstants;
import com.hxkj.common.core.PageResult;

import com.hxkj.common.utils.StringUtil;
import com.hxkj.generator.entity.GenTable;
import com.hxkj.generator.entity.GenTableColumn;
import com.hxkj.generator.mapper.GenTableColumnMapper;
import com.hxkj.generator.mapper.GenTableMapper;
import com.hxkj.generator.service.IGenerateService;
import com.hxkj.generator.util.GenUtil;
import com.hxkj.generator.validate.PageParam;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.stereotype.Service;

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

    @Override
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

    @Override
    public Object previewCode() {
        try{
            Velocity.init(getDefaultProp());
            VelocityContext context = new VelocityContext();
            context.put("hello", "Hello World!");
            StringWriter w = new StringWriter();
            Template t = Velocity.getTemplate("vm/java/controller.java.vm");
            t.merge(context, w);
            System.out.println("template:" + w);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Properties getDefaultProp(){
        Properties prop = new Properties();
//        prop.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
//        prop.setProperty(RuntimeConstants.RESOURCE_LOADER_CLASS, "classpath");
        prop.setProperty(RuntimeConstants.RESOURCE_LOADERS, "classpath");
//        prop.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        prop.setProperty("resource.loader.file.class", ClasspathResourceLoader.class.getName());
        return prop;
    }

}
