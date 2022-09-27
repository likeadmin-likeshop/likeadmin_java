package com.mdd.admin.controller.system;

import com.mdd.admin.service.system.ISystemLogsServer;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.vo.system.LogLoginVo;
import com.mdd.admin.vo.system.LogOperateVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 系统日志管理
 */
@RestController("systemLogController")
@RequestMapping("api/system/log")
public class LogsController {

    @Resource
    ISystemLogsServer iSystemLogsServer;

    /**
     * 系统操作日志
     *
     * @author fzr
     * @param params 搜索参数
     * @return AjaxResult
     */
    @GetMapping("/operate")
    public AjaxResult operate(@Validated PageParam pageParam, @RequestParam Map<String, String> params) {
        PageResult<LogOperateVo> list = iSystemLogsServer.operate(pageParam, params);
        return AjaxResult.success(list);
    }

    /**
     * 系统登录日志
     *
     * @author fzr
     * @param params 搜索参数
     * @return AjaxResult
     */
    @GetMapping("/login")
    public AjaxResult login(@Validated PageParam pageParam, @RequestParam Map<String, String> params) {
        PageResult<LogLoginVo> list = iSystemLogsServer.login(pageParam, params);
        return AjaxResult.success(list);
    }

}
