package com.mdd.generator.util;

import com.mdd.common.config.GlobalConfig;
import com.mdd.generator.constant.GenConstants;
import com.mdd.common.util.StringUtils;
import com.mdd.generator.config.GenConfig;
import com.mdd.generator.constant.HtmlConstants;
import com.mdd.generator.constant.JavaConstants;
import com.mdd.generator.constant.SqlConstants;
import com.mdd.generator.entity.GenTable;
import com.mdd.generator.entity.GenTableColumn;
import com.mdd.generator.vo.DbTableVo;
import org.apache.commons.lang3.RegExUtils;

import java.util.Arrays;

public class GenUtil {

    /**
     * 初始化表
     *
     * @author fzr
     * @param table 表
     * @param map 参数
     */
    public static void initTable(GenTable table, DbTableVo map) {
        String tableName = map.getTableName();
        String tableDesc = map.getTableComment();
        table.setTableName(tableName);
        table.setTableComment(tableDesc);
        table.setAuthorName(StringUtils.isEmpty(map.getAuthorName()) ? "LikeAdmin" : map.getAuthorName());
        table.setEntityName(GenUtil.toClassName(tableName));
        table.setModuleName(GenUtil.toModuleName(tableName));
        table.setFunctionName(GenUtil.replaceText(tableDesc));
        table.setCreateTime(System.currentTimeMillis() / 1000);
        table.setUpdateTime(System.currentTimeMillis() / 1000);
    }

    /**
     * 初始化字段列
     *
     * @author fzr
     * @param column 列
     * @param table 表
     */
    public static void initColumn(GenTableColumn column, GenTable table) {
        column.setTableId(table.getId());
        String columnName = column.getColumnName();
        String columnType = GenUtil.getDbType(column.getColumnType());
        column.setColumnLength(GenUtil.getColumnLength(column.getColumnType()));
        column.setJavaField(StringUtils.toCamelCase(columnName));
        column.setJavaType(JavaConstants.TYPE_STRING);
        column.setQueryType(GenConstants.QUERY_EQ);
        column.setUpdateTime(table.getUpdateTime());
        column.setCreateTime(table.getCreateTime());

        // 文本域组
        if (GenUtil.isArraysContains(SqlConstants.COLUMN_TYPE_STR, columnType) ||
                GenUtil.isArraysContains(SqlConstants.COLUMN_TYPE_TEXT, columnType)) {
            String columnLength = GenUtil.getColumnLength(column.getColumnType());
            String htmlType = Integer.parseInt(columnLength) >= 500 || GenUtil.isArraysContains(SqlConstants.COLUMN_TYPE_TEXT, columnType)
                    ? HtmlConstants.HTML_TEXTAREA
                    : HtmlConstants.HTML_INPUT;
            column.setHtmlType(htmlType);
        }

        // 日期字段
        else if (GenUtil.isArraysContains(SqlConstants.COLUMN_TYPE_TIME, columnType)) {
            column.setJavaType(JavaConstants.TYPE_DATE);
            column.setHtmlType(HtmlConstants.HTML_DATETIME);
        }

        // 时间字段
        else if (GenUtil.isArraysContains(SqlConstants.COLUMN_TIME_NAME, columnName)) {
            column.setJavaType(JavaConstants.TYPE_LONG);
            column.setHtmlType(HtmlConstants.HTML_DATETIME);
        }

        // 数字字段
        else if (GenUtil.isArraysContains(SqlConstants.COLUMN_TYPE_NUMBER, columnType)) {
            column.setHtmlType(HtmlConstants.HTML_INPUT);           // 输入框
            String[] str = StringUtils.split(StringUtils.substringBetween(column.getColumnType(), "(", ")"), ",");
            if (str != null && str.length == 2 && Integer.parseInt(str[1]) > 0) {
                column.setJavaType(JavaConstants.TYPE_BIG_DECIMAL); // 浮点形
            } else if (str != null && str.length == 1 && Integer.parseInt(str[0]) <= 11) {
                column.setJavaType(JavaConstants.TYPE_INTEGER);     // 整数形
            } else {
                column.setJavaType(JavaConstants.TYPE_LONG);        // 长整形
            }
        }

        // 非必填字段
        if (GenUtil.isArraysContains(SqlConstants.COLUMN_NAME_NOT_EDIT, columnName)) {
            column.setIsRequired(0);
        }

        // 需插入字段
        if (!GenUtil.isArraysContains(SqlConstants.COLUMN_NAME_NOT_ADD, columnName)) {
            column.setIsInsert(GenConstants.REQUIRE);
        }

        // 需编辑字段
        if (!GenUtil.isArraysContains(SqlConstants.COLUMN_NAME_NOT_EDIT, columnName)) {
            column.setIsEdit(GenConstants.REQUIRE);
            column.setIsRequired(GenConstants.REQUIRE);
        }

        //  需列表字段
        if (!GenUtil.isArraysContains(SqlConstants.COLUMN_NAME_NOT_LIST, columnName) && column.getIsPk() == 0) {
            column.setIsList(GenConstants.REQUIRE);
        }

        // 需查询字段
        if (!GenUtil.isArraysContains(SqlConstants.COLUMN_NAME_NOT_QUERY, columnName) && column.getIsPk() == 0) {
            column.setIsQuery(GenConstants.REQUIRE);
        }

        // 模糊查字段
        if (StringUtils.endsWithIgnoreCase(columnName, "name") ||
                columnName.equals("nickname") ||
                columnName.equals("username") ||
                columnName.equals("title")    ||
                columnName.equals("mobile")) {
            column.setQueryType(GenConstants.QUERY_LIKE);
        }

        // 根据字段设置
        if (StringUtils.endsWithIgnoreCase(columnName, "status")
                || columnName.equals("isShow")
                || columnName.equals("isDisable")) {
            // 状态字段设置单选框
            column.setHtmlType(HtmlConstants.HTML_RADIO);
        } else if (StringUtils.endsWithIgnoreCase(columnName, "type") ||
                StringUtils.endsWithIgnoreCase(columnName, "sex")) {
            // 类型&性别字段设置下拉框
            column.setHtmlType(HtmlConstants.HTML_SELECT);
        } else if (StringUtils.endsWithIgnoreCase(columnName, "image")) {
            // 图片字段设置图片上传控件
            column.setHtmlType(HtmlConstants.HTML_IMAGE_UPLOAD);
        } else if (StringUtils.endsWithIgnoreCase(columnName, "file")) {
            // 文件字段设置文件上传控件
            column.setHtmlType(HtmlConstants.HTML_FILE_UPLOAD);
        } else if (StringUtils.endsWithIgnoreCase(columnName, "content")) {
            // 内容字段的设置富文本控件
            column.setHtmlType(HtmlConstants.HTML_EDITOR);
        }
    }

    /**
     * 转业务名
     *
     * @author fzr
     * @param tableName 表名
     * @return 业务名
     */
    public static String toModuleName(String tableName)   {
        int lastIndex = tableName.lastIndexOf("_");
        int nameLength = tableName.length();
        return StringUtils.substring(tableName, lastIndex + 1, nameLength);
    }

    /**
     * 表名转Java类名
     *
     * @author fzr
     * @param tableName 表名称
     * @return 类名
     */
    public static String toClassName(String tableName) {
        String tablePrefix = GlobalConfig.tablePrefix;
        if (GenConfig.isRemoveTablePrefix && StringUtils.isNotEmpty(tablePrefix)) {
            String[] searchList = StringUtils.split(tablePrefix, ",");
            tableName = replaceFirst(tableName, searchList);
        }
        return StringUtils.convertToCamelCase(tableName);
    }

    /**
     * 批量替换前缀
     *
     * @author fzr
     * @param replaceVal 替换值
     * @param searchList 替换列表
     * @return String
     */
    public static String replaceFirst(String replaceVal, String[] searchList) {
        String text = replaceVal;
        for (String searchString : searchList) {
            if (replaceVal.startsWith(searchString)) {
                text = replaceVal.replaceFirst(searchString, "");
                break;
            }
        }
        return text;
    }

    /**
     * 关键字替换
     *
     * @author fzr
     * @param text 需要被替换的名字
     * @return 替换后的名字
     */
    public static String replaceText(String text) {
        return RegExUtils.replaceAll(text, "(?:表)", "");
    }

    /**
     * 校验数组是否包含指定值
     *
     * @author fzr
     * @param arr 数组
     * @param targetValue 值
     * @return Boolean
     */
    public static Boolean isArraysContains(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

    /**
     * 获取数据库类型字段
     *
     * @author fzr
     * @param columnType 列类型
     * @return String
     */
    public static String getDbType(String columnType) {
        if (StringUtils.indexOf(columnType, "(") > 0) {
            return StringUtils.substringBefore(columnType, "(");
        }
        else {
            return columnType;
        }
    }

    /**
     * 获取字段长度
     *
     * @author fzr
     * @param columnType 列类型
     * @return 截取后的列类型
     */
    public static String getColumnLength(String columnType) {
        if (StringUtils.indexOf(columnType, "(") > 0) {
            return StringUtils.substringBetween(columnType, "(", ")");
        } else {
            return "0";
        }
    }

    /**
     * 获取表的别名
     *
     * @param tableName 表名
     * @return String
     */
    public static String getTableAlias(String tableName) {
        if (StringUtils.isNull(tableName) || StringUtils.isEmpty(tableName)) {
            return "";
        }

        StringBuilder val = new StringBuilder();
        for (String name : tableName.split("_")) {
            val.append(name.charAt(0));
        }
        return val.toString();
    }
}
