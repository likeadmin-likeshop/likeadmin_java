package com.hxkj.admin.service.system;

import com.hxkj.admin.validate.common.PageParam;
import com.hxkj.admin.validate.system.SystemAuthAdminParam;
import com.hxkj.admin.vo.system.SystemAdminVo;
import com.hxkj.admin.vo.system.SystemSelfVo;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.entity.system.SystemAuthAdmin;

import java.util.Map;

/**
 * 系统管理员服务接口类
 */
public interface ISystemAuthAdminService {

    /**
     * 根据账号查找管理员
     *
     * @author fzr
     * @param username 主键ID
     * @return SysAdmin
     */
    SystemAuthAdmin findByUsername(String username);

    /**
     * 管理员列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @return PageResult<SysAdminListVo>
     */
    PageResult<SystemAdminVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 当前管理员
     *
     * @author fzr
     * @return SystemSelfVo
     */
    SystemSelfVo self(Integer adminId);

    /**
     * 管理员详情
     *
     * @author fzr
     * @param id 主键参数
     * @return SysAdmin
     */
    SystemAdminVo detail(Integer id);

    /**
     * 管理员新增
     *
     * @author fzr
     * @param systemAuthAdminParam 参数
     */
    void add(SystemAuthAdminParam systemAuthAdminParam);

    /**
     * 管理员编辑
     *
     * @author fzr
     * @param systemAuthAdminParam 参数
     */
    void edit(SystemAuthAdminParam systemAuthAdminParam);

    /**
     * 当前管理员更新
     *
     * @author fzr
     * @param systemAuthAdminParam 参数
     */
    void upInfo(SystemAuthAdminParam systemAuthAdminParam, Integer adminId);

    /**
     * 管理员删除
     *
     * @author fzr
     * @param id 主键参数
     */
    void del(Integer id);

    /**
     * 管理员状态切换
     *
     * @author fzr
     * @param id 主键参数
     */
    void disable(Integer id);

    /**
     * 缓存管理员
     */
    void cacheAdminUserByUid(Integer id);

}
