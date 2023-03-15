package com.mdd.admin.controller.system;

import com.mdd.admin.service.ISystemLogsServer;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.system.SystemSearchLoginsValidate;
import com.mdd.admin.validate.system.SystemSearchOperateValidate;
import com.mdd.admin.vo.system.SystemLogLoginVo;
import com.mdd.admin.vo.system.SystemLogOperateVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/system/log")
@Api(tags = "系统日志管理")
public class SystemLogsController {

    @Resource
    ISystemLogsServer iSystemLogsServer;

    @GetMapping("/operate")
    @ApiOperation(value="系统操作日志")
    public AjaxResult<PageResult<SystemLogOperateVo>> operate(@Validated PageValidate pageValidate,
                                                              @Validated SystemSearchOperateValidate searchValidate) {
        PageResult<SystemLogOperateVo> list = iSystemLogsServer.operate(pageValidate, searchValidate);
        return AjaxResult.success(list);
    }

    @GetMapping("/login")
    @ApiOperation(value="系统登录日志")
    public AjaxResult<PageResult<SystemLogLoginVo>> login(@Validated PageValidate pageValidate,
                                                          @Validated SystemSearchLoginsValidate searchValidate) {
        PageResult<SystemLogLoginVo> list = iSystemLogsServer.login(pageValidate, searchValidate);
        return AjaxResult.success(list);
    }

}
