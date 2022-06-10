package com.hxkj.generator.mapper;

import com.hxkj.common.core.basics.IBaseMapper;
import com.hxkj.generator.entity.GenTable;
import com.hxkj.generator.entity.GenTableColumn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 代码生成表
 */
@Mapper
public interface GenTableMapper extends IBaseMapper<GenTable> {

    /**
     * 查询库中的数据表
     *
     * @author fzr
     * @param params 参数
     * @return List<Map<String, String>>
     */
    @Select({"<script>",
        "SELECT table_name, table_comment, create_time, update_time " +
        "FROM information_schema.tables " +
        "WHERE table_schema = (SELECT database()) " +
        "AND table_name NOT LIKE 'qrtz_%' AND table_name NOT LIKE 'gen_%' " +
        "AND table_name NOT IN (select table_name from ls_gen_table)",
        "<if test=\"tableName != null and tableName != ''\">" +
            "AND lower(table_name) like lower(concat('%', #{tableName}, '%'))" +
        "</if>",
        "<if test=\"tableComment != null and tableComment != ''\">" +
            "AND lower(table_comment) like lower(concat('%', #{tableComment}, '%'))" +
        "</if>",
    "</script>"})
    List<Map<String, String>> selectDbTableList(Map<String, String> params);

    /**
     * 根据表名集查询表
     *
     * @author fzr
     * @param tableNames 表名集
     * @return List<Map<String, String>>
     */
    @Select({"<script>",
        "SELECT table_name, table_comment, create_time, update_time " +
        "FROM information_schema.tables " +
        "WHERE table_name NOT LIKE 'qrtz_%' AND table_name NOT LIKE 'gen_%' AND table_schema = (select database()) " +
        "AND table_name IN " +
        "<foreach collection='array' item='name' open='(' separator=',' close=')'>" +
            "#{name}" +
        "</foreach>",
    "</script>"})
    List<Map<String, String>> selectDbTableListByNames(String[] tableNames);

    /**
     * 根据表名查询列信息
     *
     * @author fzr
     * @param tableName 表名
     * @return List<GenTableColumn>
     */
    @Select({"<script>",
        "SELECT column_name, " +
            "(CASE WHEN (is_nullable = 'no' <![CDATA[ && ]]> column_key != 'PRI') THEN '1' ELSE NULL END) AS is_required, " +
            "(CASE WHEN column_key = 'PRI' THEN '1' ELSE '0' END) AS is_pk, ordinal_position AS sort, column_comment, " +
            "(CASE WHEN extra = 'auto_increment' THEN '1' ELSE '0' END) AS is_increment, column_type " +
        "FROM information_schema.columns WHERE table_schema = (SELECT database()) AND table_name = (#{tableName}) " +
        "ORDER BY ordinal_position",
    "</script>"})
    List<GenTableColumn> selectDbTableColumnsByName(String tableName);

}
