package com.hxkj.common.mapper.generate;

import com.hxkj.common.core.basics.IBaseMapper;
import com.hxkj.common.entity.generate.GenTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface GenTableMapper extends IBaseMapper<GenTable> {

    @Select(
        "SELECT table_name, table_comment, create_time, update_time " +
        "FROM information_schema.tables " +
        "WHERE table_schema = (SELECT database()) " +
        "AND table_name NOT LIKE 'qrtz_%' AND table_name NOT LIKE 'gen_%' " +
        "AND table_name NOT IN (select table_name from ls_gen_table)"
    )
    List<Map<String, String>> selectDbTableList();

}
