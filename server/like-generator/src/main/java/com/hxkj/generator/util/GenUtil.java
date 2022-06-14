package com.hxkj.generator.util;

import com.hxkj.common.utils.StringUtil;
import org.apache.commons.lang3.RegExUtils;

import java.util.Arrays;

public class GenUtil {

    /**
     * 转模块名
     *
     * @author fzr
     * @param packageName 包名
     * @return 模块名
     */
    public static String toModuleName(String packageName)
    {
        int lastIndex = packageName.lastIndexOf(".");
        int nameLength = packageName.length();
        return StringUtil.substring(packageName, lastIndex + 1, nameLength);
    }

    /**
     * 转业务名
     *
     * @author fzr
     * @param tableName 表名
     * @return 业务名
     */
    public static String toBusinessName(String tableName)
    {
        int lastIndex = tableName.lastIndexOf("_");
        int nameLength = tableName.length();
        return StringUtil.substring(tableName, lastIndex + 1, nameLength);
    }

    /**
     * 表名转Java类名
     *
     * @author fzr
     * @param tableName 表名称
     * @return 类名
     */
    public static String toClassName(String tableName)
    {
        String tablePrefix = "ls_";
        if (StringUtil.isNotEmpty(tablePrefix)) {
            String[] searchList = StringUtil.split(tablePrefix, ",");
            tableName = replaceFirst(tableName, searchList);
        }
        return StringUtil.convertToCamelCase(tableName);
    }

    /**
     * 批量替换前缀
     *
     * @author fzr
     * @param replaceVal 替换值
     * @param searchList 替换列表
     * @return String
     */
    public static String replaceFirst(String replaceVal, String[] searchList)
    {
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
    public static String replaceText(String text)
    {
        return RegExUtils.replaceAll(text, "(?:表)", "");
    }

    /**
     * 校验数组是否包含指定值
     *
     * @author fzr
     * @param arr 数组
     * @param targetValue 值
     * @return 是否包含
     */
    public static boolean isArraysContains(String[] arr, String targetValue)
    {
        return Arrays.asList(arr).contains(targetValue);
    }

    /**
     * 获取数据库类型字段
     *
     * @author fzr
     * @param columnType 列类型
     * @return 截取后的列类型
     */
    public static String getDbType(String columnType)
    {
        if (StringUtil.indexOf(columnType, "(") > 0) {
            return StringUtil.substringBefore(columnType, "(");
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
    public static Integer getColumnLength(String columnType)
    {
        if (StringUtil.indexOf(columnType, "(") > 0) {
            String length = StringUtil.substringBetween(columnType, "(", ")");
            return Integer.valueOf(length);
        }
        else {
            return 0;
        }
    }

    public static void setPkColumn() {

    }

}
