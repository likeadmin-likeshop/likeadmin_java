package com.hxkj.admin.service.system;

import com.hxkj.admin.validate.common.PageParam;
import com.hxkj.admin.validate.system.SystemAuthPostParam;
import com.hxkj.admin.vo.system.SystemAuthPostVo;
import com.hxkj.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 系统岗位服务接口类
 */
public interface ISystemAuthPostService {

    /**
     * 岗位所有
     *
     * @author fzr
     * @return List<SystemPostVo>
     */
    List<SystemAuthPostVo> all();

    /**
     * 岗位列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SystemPostVo>
     */
    PageResult<SystemAuthPostVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 岗位详情
     *
     * @author fzr
     * @param id 主键
     * @return SystemPostVo
     */
    SystemAuthPostVo detail(Integer id);

    /**
     * 岗位新增
     *
     * @author fzr
     * @param systemAuthPostParam 参数
     */
    void add(SystemAuthPostParam systemAuthPostParam);

    /**
     * 岗位编辑
     *
     * @author fzr
     * @param systemAuthPostParam 参数
     */
    void edit(SystemAuthPostParam systemAuthPostParam);

    /**
     * 岗位删除
     *
     * @author fzr
     * @param id 主键
     */
    void del(Integer id);

}
