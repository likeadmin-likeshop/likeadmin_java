package com.hxkj.admin.service.system;

import com.hxkj.admin.validate.common.PageParam;
import com.hxkj.admin.vo.system.LogLoginVo;
import com.hxkj.admin.vo.system.LogOperateVo;
import com.hxkj.common.core.PageResult;

import java.util.Map;

/**
 * 系统日志服务类接口类
 */
public interface ISystemLogServer {

    /**
     * 系统操作日志
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<LogOperateVo>
     */
    PageResult<LogOperateVo> operate(PageParam pageParam, Map<String, String> params);

    /**
     * 系统登录日志
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<LogLoginVo>
     */
    PageResult<LogLoginVo> login(PageParam pageParam, Map<String, String> params);

}
