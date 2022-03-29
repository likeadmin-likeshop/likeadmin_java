package com.hxkj.admin.service;

import com.hxkj.admin.validate.PageParam;
import com.hxkj.admin.validate.SysAdminParam;
import com.hxkj.admin.vo.system.SysAdminDetailVo;
import com.hxkj.admin.vo.system.SysAdminListVo;
import com.hxkj.common.basics.BaseService;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.entity.system.SysAdmin;

import java.util.Map;

/**
 * 系统管理员服务
 */
public interface ISysAdminService extends BaseService<SysAdmin> {

    /**
     * 根据账号查找管理员
     *
     * @author fzr
     * @param username 主键ID
     * @return SysAdmin
     */
    SysAdmin findByUsername(String username);

    /**
     * 管理员列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @return PageResult<SysAdminListVo>
     */
    PageResult<SysAdminListVo> lists(PageParam pageParam, Map<String, String> params);

    /**
     * 管理员详情
     *
     * @author fzr
     * @param id 主键参数
     * @return SysAdmin
     */
    SysAdminDetailVo detail(Integer id);

    /**
     * 新增管理员
     *
     * @author fzr
     * @param sysAdminParam 参数
     */
    void add(SysAdminParam sysAdminParam);

    /**
     * 编辑管理员
     *
     * @author fzr
     * @param sysAdminParam 参数
     */
    void edit(SysAdminParam sysAdminParam);

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
