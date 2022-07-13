package com.hxkj.generator.util;

import com.hxkj.common.utils.StringUtil;
import com.hxkj.common.utils.UrlUtil;
import com.hxkj.generator.config.GenConfig;
import com.hxkj.generator.constant.GenConstants;
import com.hxkj.generator.entity.GenTable;
import com.hxkj.generator.entity.GenTableColumn;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VelocityUtil {

    /**
     * 初始化vm方法
     */
    public static void initVelocity() {
        try {
            Properties p = new Properties();
            p.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            p.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
            Velocity.init(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置模板变量信息
     *
     * @author fzr
     * @return VelocityContext
     */
    public static VelocityContext prepareContext(GenTable table, List<GenTableColumn> columns) {
        // 处理变量字段
        boolean isSearch = false;
        List<String> fields = new LinkedList<>();
        for (GenTableColumn column : columns) {
            fields.add(column.getColumnName());
            if (column.getIsQuery() == 1) {
                isSearch = true;
            }
        }

        // 设置模板变量
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("genTpl", table.getGenTpl());
        velocityContext.put("tableName", table.getTableName());
        velocityContext.put("authorName", table.getAuthorName());
        velocityContext.put("packageName", GenConfig.packageName);
        velocityContext.put("EntityName", table.getEntityName());
        velocityContext.put("entityName", StringUtil.uncapitalize(table.getEntityName()));
        velocityContext.put("moduleName", table.getModuleName());
        velocityContext.put("functionName", StringUtil.isNotEmpty(table.getFunctionName()) ? table.getFunctionName() : "【请填写功能名称】");
        velocityContext.put("table", table);
        velocityContext.put("columns", columns);
        velocityContext.put("fields", fields);
        velocityContext.put("isSearch", isSearch);
        velocityContext.put("isEqually", VelocityUtil.getIsEqually(columns));

        return velocityContext;
    }

    /**
     * 获取生成路径
     *
     * @author fzr
     * @param table 表
     * @return String
     */
    public static String getGenPath(GenTable table) {
        String genPath = table.getGenPath();
        if (StringUtil.equals(genPath, "/")) {
            return System.getProperty("user.dir") + File.separator + "src" + File.separator;
        }
        return genPath + File.separator;
    }

    /**
     * 判断需列表字段和查询字段是否一致
     *
     * @author fzr
     * @param columns 字段列表
     * @return Boolean
     */
    public static Boolean getIsEqually(List<GenTableColumn> columns) {
        StringBuilder listStr  = new StringBuilder();
        StringBuilder queryStr = new StringBuilder();
        for (GenTableColumn col : columns) {
            if (col.getIsList() == 1) {
                listStr.append(",").append(col.getColumnName());
            }
            if (col.getIsQuery() == 1) {
                queryStr.append(",").append(col.getColumnName());
            }
        }
        return listStr.toString().equals(queryStr.toString());
    }

    /**
     * 获取模板列表
     *
     * @author fzr
     * @return List<String>
     */
    public static List<String> getTemplateList(String genTpl, List<GenTableColumn> columns) {
        List<String> templates = new LinkedList<>();
        templates.add("java/controller.java.vm");
        templates.add("java/entity.java.vm");
        templates.add("java/mapper.java.vm");
        templates.add("java/service.java.vm");
        templates.add("java/serviceImpl.java.vm");
        templates.add("java/validate.java.vm");
        if (VelocityUtil.getIsEqually(columns)) {
            templates.add("java/vo.java.vm");
        } else {
            templates.add("java/voList.java.vm");
            templates.add("java/voDetail.java.vm");
        }
//        if (GenConstants.TPL_CRUD.equals(genTpl)) {
//            templates.add("vue/index.vue.vm");
//        }
//        else if (GenConstants.TPL_TREE.equals(genTpl)) {
//            templates.add("vue/index-tree.vue.vm");
//        }
        return templates;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, GenTable genTable) {
        String fileName = "";
        String entityName   = genTable.getEntityName();
        String moduleName   = genTable.getModuleName();

        if (template.contains("mapper.java.vm")) {
            fileName = StringUtil.format("{}/mapper/{}/{}Mapper.java", GenConfig.commonPackage, moduleName, entityName);
        }

        else if (template.contains("entity.java.vm")) {
            fileName = StringUtil.format("{}/entity/{}/{}Entity.java", GenConfig.commonPackage, moduleName, entityName);
        }

        else if (template.contains("service.java.vm")) {
            fileName = StringUtil.format("{}/service/{}/I{}Service.java", GenConfig.adminPackage, moduleName, entityName);
        }

        else if (template.contains("serviceImpl.java.vm")) {
            fileName = StringUtil.format("{}/service/{}/impl/{}ServiceImpl.java", GenConfig.adminPackage, moduleName, entityName);
        }

        else if (template.contains("controller.java.vm")) {
            fileName = StringUtil.format("{}/controller/{}/{}Controller.java", GenConfig.adminPackage, moduleName, entityName);
        }

        else if (template.contains("validate.java.vm")) {
            fileName = StringUtil.format("{}/validate/{}/{}Param.java", GenConfig.adminPackage, moduleName, entityName);
        }

        else if (template.contains("vo.java.vm")) {
            fileName = StringUtil.format("{}/vo/{}/{}Vo.java", GenConfig.adminPackage, moduleName, entityName);
        }

        else if (template.contains("voList.java.vm")) {
            fileName = StringUtil.format("{}/vo/{}/{}ListVo.java", GenConfig.adminPackage, moduleName, entityName);
        }

        else if (template.contains("voDetail.java.vm")) {
            fileName = StringUtil.format("{}/vo/{}/{}DetailVo.java", GenConfig.adminPackage, moduleName, entityName);
        }

        return fileName;
    }

}
