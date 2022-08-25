package com.mdd.admin.controller.channel;

import com.mdd.admin.config.aop.Log;
import com.mdd.admin.service.channel.IChannelH5Service;
import com.mdd.admin.service.channel.IChannelWxService;
import com.mdd.common.core.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 微信开发平台渠道设置
 */
@RestController
@RequestMapping("api/channel/wx")
public class WxController {

    @Resource
    IChannelWxService iChannelWxService;

    /**
     * H5渠道配置详情
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail() {
        Map<String, Object> map = iChannelWxService.detail();
        return AjaxResult.success(map);
    }

    /**
     * H5渠道配置保存
     *
     * @author fzr
     * @param param 参数
     * @return Object
     */
    @Log(title = "微信开放平台渠道保存")
    @PostMapping("/save")
    public Object save(@RequestBody Map<String, String> param) {
        iChannelWxService.save(param);
        return AjaxResult.success();
    }

}
