package com.hxkj.admin.service;

import com.hxkj.admin.validate.PageParam;
import com.hxkj.admin.validate.system.SystemRoleParam;
import com.hxkj.admin.vo.system.SystemRoleVo;
import com.hxkj.common.core.PageResult;
import org.springframework.validation.annotation.Validated;

/**
 * 系统角色服务
 */
public interface ISystemRoleService {

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
    PageResult<SystemRoleVo> lists(@Validated PageParam pageParam);

    /**
     * 角色详情
     *
     * @author fzr
     * @param id 主键参数
     * @return SysRole
     */
    SystemRoleVo detail(Integer id);

    /**
     * 新增角色
     *
     * @author fzr
     * @param systemRoleParam 参数
     */
    void add(SystemRoleParam systemRoleParam);

    /**
     * 更新角色
     *
     * @author fzr
     * @param systemRoleParam 参数
     */
    void edit(SystemRoleParam systemRoleParam);

    /**
     * 删除角色
     *
     * @author fzr
     * @param id 主键参数
     */
    void del(Integer id);

}
