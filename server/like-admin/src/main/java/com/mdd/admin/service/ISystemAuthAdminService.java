package com.mdd.admin.service;

import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.system.SystemAdminCreateValidate;
import com.mdd.admin.validate.system.SystemAdminSearchValidate;
import com.mdd.admin.validate.system.SystemAdminUpInfoValidate;
import com.mdd.admin.validate.system.SystemAdminUpdateValidate;
import com.mdd.admin.vo.system.SystemAuthAdminDetailVo;
import com.mdd.admin.vo.system.SystemAuthAdminListedVo;
import com.mdd.admin.vo.system.SystemAuthAdminSelvesVo;
import com.mdd.common.core.PageResult;

/**
 * 系统管理员服务接口类
 */
public interface ISystemAuthAdminService {

    /**
     * 管理员列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<SystemAuthAdminListedVo>
     */
    PageResult<SystemAuthAdminListedVo> list(PageValidate pageValidate, SystemAdminSearchValidate searchValidate);

    /**
     * 当前管理员
     *
     * @author fzr
     * @return SystemSelfVo
     */
    SystemAuthAdminSelvesVo self(Integer adminId);

    /**
     * 管理员详情
     *
     * @author fzr
     * @param id 主键参数
     * @return SystemAuthAdminDetailVo
     */
    SystemAuthAdminDetailVo detail(Integer id);

    /**
     * 管理员新增
     *
     * @author fzr
     * @param createValidate 参数
     */
    void add(SystemAdminCreateValidate createValidate);

    /**
     * 管理员编辑
     *
     * @author fzr
     * @param updateValidate 参数
     * @param adminId 管理员ID
     */
    void edit(SystemAdminUpdateValidate updateValidate, Integer adminId);

    /**
     * 当前管理员更新
     *
     * @author fzr
     * @param upInfoValidate 参数
     * @param adminId 管理员ID
     */
    void upInfo(SystemAdminUpInfoValidate upInfoValidate, Integer adminId);

    /**
     * 管理员删除
     *
     * @author fzr
     * @param id 主键参数
     * @param adminId 管理员ID
     */
    void del(Integer id, Integer adminId);

    /**
     * 管理员状态切换
     *
     * @author fzr
     * @param id 主键参数
     */
    void disable(Integer id, Integer adminId);

}
