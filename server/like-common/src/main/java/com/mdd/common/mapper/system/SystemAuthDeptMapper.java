package com.mdd.common.mapper.system;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.common.entity.system.SystemAuthDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 系统岗位Mapper
 */
@Mapper
public interface SystemAuthDeptMapper extends IBaseMapper<SystemAuthDept> {

    /**
     * 级联更新部门状态
     * @param stopStatus
     * @param id
     * @return
     */
    @Update(value = "WITH recursive temp AS (SELECT id, pid\n"
            + "                        FROM la_system_auth_dept\n"
            + "                        WHERE id = #{id}\n" + "                        UNION ALL\n"
            + "                        SELECT u.id, u.pid\n"
            + "                        FROM la_system_auth_dept u,\n"
            + "                             temp t\n"
            + "                        WHERE u.pid = t.id and u.is_stop <> #{stopStatus})\n"
            + "update la_system_auth_dept set is_stop = #{stopStatus}\n"
            + "where id in (select id from temp)")
    void updateChilder(@Param("stopStatus") int stopStatus, @Param("id") Integer id);
}
