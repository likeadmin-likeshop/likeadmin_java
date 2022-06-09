package com.hxkj.common.mapper.generate;

import com.hxkj.common.core.basics.IBaseMapper;
import com.hxkj.common.entity.generate.GenTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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

}
