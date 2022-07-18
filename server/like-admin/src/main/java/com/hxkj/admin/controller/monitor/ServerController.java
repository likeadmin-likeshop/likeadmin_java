package com.hxkj.admin.controller.monitor;

import com.hxkj.admin.config.aop.Log;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.core.ServerResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务监控管理
 */
@RestController
@RequestMapping("api/monitor")
public class ServerController {
    /**
     * 服务器信息
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "服务监控")
    @GetMapping("/server")
    public Object info() {
        ServerResult server = new ServerResult();
        return AjaxResult.success(server.copyTo());
    }

}
