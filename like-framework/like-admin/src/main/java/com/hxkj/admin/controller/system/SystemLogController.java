package com.hxkj.admin.controller.system;

import com.hxkj.admin.service.ISystemLogServer;
import com.hxkj.admin.validate.PageParam;
import com.hxkj.admin.vo.system.LogLoginVo;
import com.hxkj.admin.vo.system.LogOperateVo;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.core.PageResult;
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
@RestController
@RequestMapping("/system/log")
public class SystemLogController {

    @Resource
    ISystemLogServer iSystemLogServer;

    /**
     * 系统操作日志
     *
     * @author fzr
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/operate")
    public Object operate(@Validated PageParam pageParam, @RequestParam Map<String, String> params) {
        PageResult<LogOperateVo> list = iSystemLogServer.operate(pageParam, params);
        return AjaxResult.success(list);
    }

    /**
     * 系统登录日志
     *
     * @author fzr
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/login")
    public Object login(@Validated PageParam pageParam, @RequestParam Map<String, String> params) {
        PageResult<LogLoginVo> list = iSystemLogServer.login(pageParam, params);
        return AjaxResult.success(list);
    }

}
