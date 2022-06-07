package com.hxkj.admin.service;

import com.hxkj.admin.validate.PageParam;
import com.hxkj.admin.validate.system.SystemPostParam;
import com.hxkj.admin.vo.system.SystemPostVo;
import com.hxkj.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 系统岗位服务接口类
 */
public interface ISystemPostService {

    /**
     * 岗位所有
     *
     * @author fzr
     * @return List<SystemPostVo>
     */
    List<SystemPostVo> all();

    /**
     * 岗位列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SystemPostVo>
     */
    PageResult<SystemPostVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 岗位详情
     *
     * @author fzr
     * @param id 主键
     * @return SystemPostVo
     */
    SystemPostVo detail(Integer id);

    /**
     * 岗位新增
     *
     * @author fzr
     * @param systemPostParam 参数
     */
    void add(SystemPostParam systemPostParam);

    /**
     * 岗位编辑
     *
     * @author fzr
     * @param systemPostParam 参数
     */
    void edit(SystemPostParam systemPostParam);

    /**
     * 岗位删除
     *
     * @author fzr
     * @param id 主键
     */
    void del(Integer id);

}
