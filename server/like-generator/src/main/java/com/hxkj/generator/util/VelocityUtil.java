package com.hxkj.generator.util;

import com.hxkj.common.utils.StringUtil;
import com.hxkj.generator.entity.GenTable;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class VelocityUtil {

    /**
     * 初始化vm方法
     */
    public static void initVelocity()
    {
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
     * @return 模板列表
     */
    public static VelocityContext prepareContext(GenTable genTable)
    {
        String moduleName = genTable.getModuleName();
        String businessName = genTable.getBusinessName();
        String packageName = genTable.getPackageName();
        String tplCategory = genTable.getGenTpl();
        String functionName = genTable.getFunctionName();

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("tplCategory", genTable.getGenTpl());
        velocityContext.put("tableName", genTable.getTableName());
        velocityContext.put("functionName", StringUtil.isNotEmpty(functionName) ? functionName : "【请填写功能名称】");
        velocityContext.put("ClassName", genTable.getEntityName());
        return velocityContext;
    }

    /**
     * 获取模板信息
     *
     * @return 模板列表
     */
    public static List<String> getTemplateList(String tplCategory)
    {
        List<String> templates = new ArrayList<String>();
//        templates.add("vm/java/domain.java.vm");
//        templates.add("vm/java/mapper.java.vm");
//        templates.add("vm/java/service.java.vm");
//        templates.add("vm/java/serviceImpl.java.vm");
        templates.add("vm/java/controller.java.vm");
//        templates.add("vm/xml/mapper.xml.vm");
//        templates.add("vm/sql/sql.vm");
//        templates.add("vm/js/api.js.vm");
//        if (GenConstants.TPL_CRUD.equals(tplCategory))
//        {
//            templates.add("vm/vue/index.vue.vm");
//        }
//        else if (GenConstants.TPL_TREE.equals(tplCategory))
//        {
//            templates.add("vm/vue/index-tree.vue.vm");
//        }
//        else if (GenConstants.TPL_SUB.equals(tplCategory))
//        {
//            templates.add("vm/vue/index.vue.vm");
//            templates.add("vm/java/sub-domain.java.vm");
//        }
        return templates;
    }


}
