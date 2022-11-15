package com.mdd.admin.service;

import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.system.SystemSearchLoginsValidate;
import com.mdd.admin.validate.system.SystemSearchOperateValidate;
import com.mdd.admin.vo.system.SystemLogLoginVo;
import com.mdd.admin.vo.system.SystemLogOperateVo;
import com.mdd.common.core.PageResult;

/**
 * 系统日志服务类接口类
 */
public interface ISystemLogsServer {

    /**
     * 系统操作日志
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<LogOperateVo>
     */
    PageResult<SystemLogOperateVo> operate(PageValidate pageValidate, SystemSearchOperateValidate searchValidate);

    /**
     * 系统登录日志
     *
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<LogLoginVo>
     */
    PageResult<SystemLogLoginVo> login(PageValidate pageValidate, SystemSearchLoginsValidate searchValidate);

}
