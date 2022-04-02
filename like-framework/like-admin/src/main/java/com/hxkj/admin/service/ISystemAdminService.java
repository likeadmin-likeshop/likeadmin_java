package com.hxkj.admin.service;

import com.hxkj.admin.validate.PageParam;
import com.hxkj.admin.validate.system.SystemAdminParam;
import com.hxkj.admin.vo.system.SystemAdminVo;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.entity.system.SystemAdmin;

import java.util.Map;

/**
 * 系统管理员服务接口类
 */
public interface ISystemAdminService {

    /**
     * 根据账号查找管理员
     *
     * @author fzr
     * @param username 主键ID
     * @return SysAdmin
     */
    SystemAdmin findByUsername(String username);

    /**
     * 管理员列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @return PageResult<SysAdminListVo>
     */
    PageResult<SystemAdminVo> lists(PageParam pageParam, Map<String, String> params);

    /**
     * 管理员详情
     *
     * @author fzr
     * @param id 主键参数
     * @return SysAdmin
     */
    SystemAdminVo detail(Integer id);

    /**
     * 新增管理员
     *
     * @author fzr
     * @param systemAdminParam 参数
     */
    void add(SystemAdminParam systemAdminParam);

    /**
     * 编辑管理员
     *
     * @author fzr
     * @param systemAdminParam 参数
     */
    void edit(SystemAdminParam systemAdminParam);

    /**
     * 删除管理员
     *
     * @author fzr
     * @param id 主键参数
     */
    void del(Integer id);

    /**
     * 缓存管理员
     */
    void cacheAdminUserByUid(Integer id);

}
