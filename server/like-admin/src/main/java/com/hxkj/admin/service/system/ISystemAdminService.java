package com.hxkj.admin.service.system;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.hxkj.admin.validate.common.PageParam;
import com.hxkj.admin.validate.system.SystemAdminParam;
import com.hxkj.admin.validate.system.SystemDeptParam;
import com.hxkj.admin.vo.system.SystemAdminVo;
import com.hxkj.admin.vo.system.SystemDeptVo;
import com.hxkj.admin.vo.system.SystemSelfVo;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.entity.system.SystemAdmin;
import com.hxkj.common.entity.system.SystemDept;
import com.hxkj.common.mapper.system.SystemAdminMapper;
import com.hxkj.common.mapper.system.SystemDeptMapper;
import com.hxkj.common.utils.ArrayUtil;
import com.hxkj.common.utils.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
