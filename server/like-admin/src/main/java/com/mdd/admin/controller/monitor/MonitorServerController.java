package com.mdd.admin.controller.monitor;

import com.mdd.admin.config.aop.Log;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.ServerResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 服务监控管理
 */
@RestController
@RequestMapping("api/monitor")
public class MonitorServerController {

    /**
     * 服务器信息
     *
     * @author fzr
     * @return AjaxResult<Map<String, Object>>
     */
    @Log(title = "服务监控")
    @GetMapping("/server")
    public AjaxResult<Map<String, Object>> info() {
        ServerResult server = new ServerResult();
        return AjaxResult.success(server.copyTo());
    }

}
