package com.hxkj.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.hxkj.common.core.PageResult;

import com.hxkj.common.exception.OperateException;
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
import com.hxkj.generator.vo.DbTableVo;
import com.hxkj.generator.vo.GenColumnVo;
import com.hxkj.generator.vo.GenTableVo;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器服务实现类
 */
@Service
public class GenerateServiceImpl implements IGenerateService {

    private static final Logger log = LoggerFactory.getLogger(GenerateServiceImpl.class);

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
    public PageResult<DbTableVo> db(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        PageHelper.startPage(page, limit);
        List<DbTableVo> tables = genTableMapper.selectDbTableList(params);

        for (DbTableVo vo : tables) {
            if (vo.getUpdateTime() == null) {
                vo.setUpdateTime("");
            }
        }

        return PageResult.pageHelper(tables);
    }

    /**
     * 生成列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<Map<String, Object>>
     */
    @Override
    public PageResult<GenTableVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<GenTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.select("id,gen_tpl,entity_name,table_name,table_comment,create_time,update_time");

        genTableMapper.setSearch(queryWrapper, params, new String[]{
                "like:tableName@table_name:str",
                "like:tableComment@table_comment:str",
                "datetime:startTime-endTime@create_time:str"
        });

        IPage<GenTable> iPage = genTableMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<GenTableVo> list = new LinkedList<>();
        for (GenTable item : iPage.getRecords()) {
            GenTableVo vo = new GenTableVo();
            BeanUtils.copyProperties(item, vo);
            vo.setCreateTime(TimeUtil.timestampToDate(item.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(item.getUpdateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 生成详情
     *
     * @author fzr
     * @return Object
     */
    @Override
    public Map<String, Object> detail(Integer id) {
        Map<String, Object> maps = new LinkedHashMap<>();
        GenTable genTable = genTableMapper.selectById(id);
        Assert.notNull(genTable, "查询的数据不存在");

        // 基本信息
        Map<String, Object> base = new LinkedHashMap<>();
        base.put("id", genTable.getId());
        base.put("tableName", genTable.getTableName());
        base.put("tableComment", genTable.getTableComment());
        base.put("entityName", genTable.getEntityName());
        base.put("authorName", genTable.getAuthorName());
        base.put("remarks", genTable.getRemarks());
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
        QueryWrapper<GenTableColumn> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("table_id", id);
        queryWrapper.orderByAsc("sort");
        List<GenColumnVo> columns = new LinkedList<>();
        for (GenTableColumn item : genTableColumnMapper.selectList(queryWrapper)) {
            GenColumnVo vo = new GenColumnVo();
            BeanUtils.copyProperties(item, vo);
            vo.setCreateTime(TimeUtil.timestampToDate(item.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(item.getUpdateTime()));
            columns.add(vo);
        }

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
        try {
            List<Map<String, String>> tables = genTableMapper.selectDbTableListByNames(tableNames);
            for (Map<String, String> map : tables) {
                // 生成表信息
                GenTable table = new GenTable();
                GenUtil.initTable(table, map);
                int row = genTableMapper.insert(table);

                // 生成列信息
                if (row > 0) {
                    String tableName = map.get("table_name");
                    List<GenTableColumn> genTableColumns = genTableMapper.selectDbTableColumnsByName(tableName);
                    for (GenTableColumn column : genTableColumns) {
                        GenUtil.initColumn(column, table);
                        genTableColumnMapper.insert(column);
                    }
                }
            }
        } catch (Exception e) {
            throw new OperateException("导入失败：" + e.getMessage());
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
        model.setTableComment(genParam.getTableComment());
        model.setAuthorName(genParam.getAuthorName());
        model.setEntityName(genParam.getEntityName());
        model.setModuleName(genParam.getModuleName());
        model.setPackageName(genParam.getPackageName());
        model.setBusinessName(genParam.getBusinessName());
        model.setFunctionName(genParam.getFunctionName());
        model.setRemarks(genParam.getRemarks());
        model.setGenTpl(genParam.getGenTpl());
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
     * @param id 主键
     */
    @Override
    @Transactional
    public void syncTable(Integer id) {
        // 原表数据
        GenTable genTable = genTableMapper.selectById(id);
        List<GenTableColumn> genTableColumns = genTableColumnMapper.selectList(
                new QueryWrapper<GenTableColumn>()
                        .eq("table_id", id)
                        .orderByAsc("sort"));

        // 原表转Map
        Map<String, GenTableColumn> tableColumnMap = genTableColumns
                .stream().collect(Collectors.toMap(GenTableColumn::getColumnName, Function.identity()));

        // 新表数据
        List<GenTableColumn> columns = genTableMapper.selectDbTableColumnsByName(genTable.getTableName());
        if (StringUtil.isNull(columns)) {
            throw new OperateException("同步结构失败,原表结构不存在！");
        }

        // 处理更新字段
        columns.forEach(column -> {
            GenUtil.initColumn(column, genTable);
            if (tableColumnMap.containsKey(column.getColumnName())) {
                GenTableColumn prevColumn = tableColumnMap.get(column.getColumnName());
                column.setId(prevColumn.getId());

                if (column.getIsList() != null && column.getIsList() == 1) {
                    column.setDictType(prevColumn.getDictType());
                    column.setQueryType(prevColumn.getQueryType());
                }

                if (prevColumn.getIsRequired() == 1
                        && column.getIsPk() == 0
                        && (column.getIsInsert() == 1 || column.getIsEdit() == 1)) {
                    column.setHtmlType(prevColumn.getHtmlType());
                    column.setIsRequired(prevColumn.getIsRequired());
                }

                genTableColumnMapper.updateById(column);
            } else {
                genTableColumnMapper.insert(column);
            }
        });

        // 删除弃用字段
        List<String> dbTableColumnNames = columns.stream().map(GenTableColumn::getColumnName).collect(Collectors.toList());
        List<GenTableColumn> delColumns = genTableColumns.stream()
                .filter(column -> !dbTableColumnNames.contains(column.getColumnName()))
                .collect(Collectors.toList());
        if (StringUtil.isNotEmpty(delColumns)) {
            for (GenTableColumn item : delColumns) {
                genTableColumnMapper.deleteById(item);
            }
        }
    }

    /**
     * 预览代码
     *
     * @author fzr
     * @param id 主键
     * @return Map<String, String>
     */
    @Override
    public Map<String, String> previewCode(Integer id) {
        GenTable table = genTableMapper.selectById(id);
        List<GenTableColumn> columns = genTableColumnMapper.selectList(
                new QueryWrapper<GenTableColumn>()
                        .eq("table_id", id)
                        .orderByAsc("sort"));

        // 初始模板
        VelocityUtil.initVelocity();
        VelocityContext context = VelocityUtil.prepareContext(table, columns);

        // 渲染模板
        Map<String, String> map = new LinkedHashMap<>();
        List<String> templates = VelocityUtil.getTemplateList("curd");
        for (String template : templates) {
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);
            map.put(template, sw.toString());
            System.out.println(sw);
        }

        return map;
    }

    /**
     * 下载代码
     *
     * @author fzr
     * @param tableNames 表名集合
     * @return byte[]
     */
    @Override
    public byte[] downloadCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            genZipCode(tableName, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 生成代码
     *
     * @param tableName 表名
     * @param zip 压缩包
     */
    private void genZipCode(String tableName, ZipOutputStream zip) {
        // 查表信息
        GenTable table = genTableMapper.selectOne(new QueryWrapper<GenTable>()
                .eq("table_name", tableName)
                .last("limit 1"));

        // 查列信息
        List<GenTableColumn> columns = genTableColumnMapper.selectList(
                new QueryWrapper<GenTableColumn>()
                        .orderByAsc("sort")
                        .eq("table_id", table.getId()));

        // 初始模板
        VelocityUtil.initVelocity();
        VelocityContext context = VelocityUtil.prepareContext(table, columns);

        // 渲染模板
        List<String> templates = VelocityUtil.getTemplateList(table.getGenTpl());
        for (String template : templates) {
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);
            try {
                zip.putNextEntry(new ZipEntry(VelocityUtil.getFileName(template, table)));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                zip.flush();
                zip.closeEntry();
            } catch (IOException e) {
                log.error("生成渲染模板失败: " + e.getMessage());
            }
        }
    }

}
