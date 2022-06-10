package com.hxkj.generator.service;

import com.alibaba.fastjson.JSONArray;
import com.hxkj.generator.validate.system.SystemDeptParam;
import com.hxkj.generator.vo.system.SystemDeptVo;

import java.util.List;
import java.util.Map;

/**
 * 系统部门服务接口类
 */
public interface ISystemDeptService {

    /**
     * 部门所有
     *
     * @author fzr
     * @return List<SystemDeptVo>
     */
    List<SystemDeptVo> all();

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
    SystemDeptVo detail(Integer id);

    /**
     * 部门新增
     *
     * @author fzr
     * @param systemDeptParam 参数
     */
    void add(SystemDeptParam systemDeptParam);

    /**
     * 部门编辑
     *
     * @author fzr
     * @param systemDeptParam 参数
     */
    void edit(SystemDeptParam systemDeptParam);

    /**
     * 部门删除
     *
     * @author fzr
     * @param id 主键
     */
    void del(Integer id);

}
