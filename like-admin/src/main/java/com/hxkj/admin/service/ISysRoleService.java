package com.hxkj.admin.service;

import com.hxkj.admin.validate.PageParam;
import com.hxkj.admin.validate.SysRoleParam;
import com.hxkj.admin.vo.system.SysRoleListVo;
import com.hxkj.common.basics.BaseService;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.entity.system.SysRole;
import org.springframework.validation.annotation.Validated;

/**
 * 系统角色服务
 */
public interface ISysRoleService extends BaseService<SysRole> {

    /**
     * 根据id获取角色名称
     * @param id 角色ID
     * @return String
     */
    String getRoleNameById(Integer id);

    /**
     * 角色列表
     *
     * @author fzr
     * @param pageParam 参数
     * @return PageResult<SysRoleListVo>
     */
    PageResult<SysRoleListVo> lists(@Validated PageParam pageParam);

    /**
     * 角色详情
     *
     * @author fzr
     * @param id 主键参数
     * @return SysRole
     */
    SysRole detail(Integer id);

    /**
     * 新增角色
     *
     * @author fzr
     * @param sysRoleParam 参数
     */
    void add(SysRoleParam sysRoleParam);

    /**
     * 更新角色
     *
     * @author fzr
     * @param sysRoleParam 参数
     */
    void edit(SysRoleParam sysRoleParam);

    /**
     * 删除角色
     *
     * @author fzr
     * @param id 主键参数
     */
    void del(Integer id);

}
