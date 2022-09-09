package com.mdd.admin.service.system;

import com.alibaba.fastjson.JSONArray;
import com.mdd.admin.validate.system.SystemAuthDeptParam;
import com.mdd.admin.vo.system.SystemAuthDeptVo;

import java.util.List;
import java.util.Map;

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
     * @param params 搜索参数
     * @return JSONArray
     */
    JSONArray list(Map<String, String> params);

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
     * @param systemAuthDeptParam 参数
     */
    void add(SystemAuthDeptParam systemAuthDeptParam);

    /**
     * 部门编辑
     *
     * @author fzr
     * @param systemAuthDeptParam 参数
     */
    void edit(SystemAuthDeptParam systemAuthDeptParam);

    /**
     * 部门删除
     *
     * @author fzr
     * @param id 主键
     */
    void del(Integer id);

}
