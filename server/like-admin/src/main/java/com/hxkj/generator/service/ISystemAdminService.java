package com.hxkj.generator.service;

import com.hxkj.generator.validate.PageParam;
import com.hxkj.generator.validate.system.SystemAdminParam;
import com.hxkj.generator.vo.system.SystemAdminVo;
import com.hxkj.generator.vo.system.SystemSelfVo;
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
     * @param systemAdminParam 参数
     */
    void add(SystemAdminParam systemAdminParam);

    /**
     * 管理员编辑
     *
     * @author fzr
     * @param systemAdminParam 参数
     */
    void edit(SystemAdminParam systemAdminParam);

    /**
     * 当前管理员更新
     *
     * @author fzr
     * @param systemAdminParam 参数
     */
    void upInfo(SystemAdminParam systemAdminParam, Integer adminId);

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
