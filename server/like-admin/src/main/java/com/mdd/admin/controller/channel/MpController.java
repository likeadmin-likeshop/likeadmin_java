package com.mdd.admin.controller.channel;

import com.mdd.admin.config.aop.Log;
import com.mdd.admin.service.channel.IChannelMpService;
import com.mdd.admin.validate.channel.ChannelMpParam;
import com.mdd.admin.vo.channel.ChannelMpVo;
import com.mdd.common.core.AjaxResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 微信小程序渠道设置
 */
@RestController
@RequestMapping("api/channel/mp")
public class MpController {

    @Resource
    IChannelMpService iChannelMpService;

    /**
     * 微信小程序渠道设置详情
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/detail")
    public AjaxResult detail() {
        ChannelMpVo vo = iChannelMpService.detail();
        return AjaxResult.success(vo);
    }

    /**
     * 微信小程序渠道设置保存
     *
     * @author fzr
     * @param channelMpParam 参数
     * @return AjaxResult
     */
    @Log(title = "微信小程序渠道设置保存")
    @PostMapping("/save")
    public AjaxResult save(@Validated @RequestBody ChannelMpParam channelMpParam) {
        iChannelMpService.save(channelMpParam);
        return AjaxResult.success();
    }

}
