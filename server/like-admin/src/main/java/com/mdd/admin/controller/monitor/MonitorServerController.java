package com.mdd.admin.controller.monitor;

import com.mdd.admin.aop.Log;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.ServerResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/monitor")
@Api(tags = "监控服务管理")
public class MonitorServerController {

    /**
     * 服务器信息
     *
     * @author fzr
     * @return AjaxResult<Map<String, Object>>
     */
    @Log(title = "服务监控")
    @GetMapping("/server")
    @ApiOperation(value="服务监控")
    public AjaxResult<Map<String, Object>> info() {
        ServerResult server = new ServerResult();
        return AjaxResult.success(server.copyTo());
    }

}
