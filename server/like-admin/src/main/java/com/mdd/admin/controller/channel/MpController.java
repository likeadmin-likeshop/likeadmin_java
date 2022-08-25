package com.mdd.admin.controller.channel;

import com.mdd.admin.config.aop.Log;
import com.mdd.admin.service.channel.IChannelMpService;
import com.mdd.common.core.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 微信小程序渠道设置
 */
@RestController
@RequestMapping("api/channel/mp")
public class MpController {

    @Resource
    IChannelMpService iChannelMpService;

    /**
     * 微信小程序渠道配置详情
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail() {
        Map<String, Object> map = iChannelMpService.detail();
        return AjaxResult.success(map);
    }

    /**
     * 微信小程序渠道配置保存
     *
     * @author fzr
     * @param param 参数
     * @return Object
     */
    @Log(title = "微信小程序渠道保存")
    @PostMapping("/save")
    public Object save(@RequestBody Map<String, String> param) {
        iChannelMpService.save(param);
        return AjaxResult.success();
    }

}
