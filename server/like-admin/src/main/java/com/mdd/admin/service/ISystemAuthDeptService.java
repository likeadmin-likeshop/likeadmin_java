package com.mdd.admin.service;

import com.alibaba.fastjson2.JSONArray;
import com.mdd.admin.validate.system.SystemDeptCreateValidate;
import com.mdd.admin.validate.system.SystemDeptSearchValidate;
import com.mdd.admin.validate.system.SystemDeptUpdateValidate;
import com.mdd.admin.vo.system.SystemAuthDeptVo;

import java.util.List;

/**
 * 系统部门服务接口类
 */
public interface ISystemAuthDeptService {

    /**
     * 部门所有
     *
     * @author fzr
     * @return List<SystemDeptVo>
     */
    List<SystemAuthDeptVo> all();

    /**
     * 部门列表
     *
     * @author fzr
     * @param searchValidate 搜索参数
     * @return JSONArray
     */
    JSONArray list(SystemDeptSearchValidate searchValidate);

    /**
     * 部门详情
     *
     * @author fzr
     * @param id 主键
     * @return SysMenu
     */
    SystemAuthDeptVo detail(Integer id);

    /**
     * 部门新增
     *
     * @author fzr
     * @param createValidate 参数
     */
    void add(SystemDeptCreateValidate createValidate);

    /**
     * 部门编辑
     *
     * @author fzr
     * @param updateValidate 参数
     */
    void edit(SystemDeptUpdateValidate updateValidate);

    /**
     * 部门删除
     *
     * @author fzr
     * @param id 主键
     */
    void del(Integer id);

}
