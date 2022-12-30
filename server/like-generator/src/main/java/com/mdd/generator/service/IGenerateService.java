package com.mdd.generator.service;

import com.mdd.common.core.PageResult;
import com.mdd.generator.validate.GenParam;
import com.mdd.generator.validate.PageParam;
import com.mdd.generator.vo.DbColumnVo;
import com.mdd.generator.vo.DbTableVo;
import com.mdd.generator.vo.GenTableVo;

import java.util.List;
import java.util.Map;

/**
 * 代码生成接口服务类
 */
public interface IGenerateService {

    /**
     * 库列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<Map<String, String>>
     */
    PageResult<DbTableVo> db(PageParam pageParam, Map<String, String> params);

    /**
     * 所有库表
     *
     * @author fzr
     * @param params 搜索参数
     * @return PageResult<Map<String, String>>
     */
    List<DbTableVo> dbAll(Map<String, String> params);

    /**
     * 根据表名查字段
     *
     * @param tableName 表名
     * @return List<DbColumnVo>
     */
    List<DbColumnVo> dbColumn(String tableName);

    /**
     * 生成列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    PageResult<GenTableVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 生成详情
     *
     * @author fzr
     * @param id 主键
     * @return Object
     */
    Map<String, Object> detail(Integer id);

    /**
     * 导入表结构
     *
     * @author fzr
     * @param tableNames 参数
     */
    void importTable(String[] tableNames);

    /**
     * 编辑表结构
     *
     * @param  genParam 参数
     * @author fzr
     */
    void editTable(GenParam genParam);

    /**
     * 删除表结构
     *
     * @param ids 主键
     * @author fzr
     */
    void deleteTable(List<Integer> ids);

    /**
     * 同步数据表
     *
     * @param id 主键
     * @author fzr
     */
    void syncTable(Integer id);

    /**
     * 预览代码
     *
     * @author fzr
     * @return Map<String, String>
     */
    Map<String, String> previewCode(Integer id);

    /**
     * 生成代码
     *
     * @author fzr
     * @param tableName 表名
     */
    void genCode(String tableName);

    /**
     * 下载代码
     *
     * @author fzr
     * @param tableNames 表名集合
     * @return Object
     */
    byte[] downloadCode(String[] tableNames);

}
