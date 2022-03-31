package com.hxkj.admin.controller.monitor;

import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.core.ServerResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/monitor")
public class ServerController
{
    /**
     * 服务器信息
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/server")
    public Object info()
    {
        ServerResult server = new ServerResult();
        return AjaxResult.success(server.copyTo());
    }

}
