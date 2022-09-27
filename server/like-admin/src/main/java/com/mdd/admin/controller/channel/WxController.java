package com.mdd.admin.controller.channel;

import com.mdd.admin.config.aop.Log;
import com.mdd.admin.service.channel.IChannelWxService;
import com.mdd.admin.validate.channel.ChannelWxParam;
import com.mdd.admin.vo.channel.ChannelWxVo;
import com.mdd.common.core.AjaxResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 微信开发平台渠道设置
 */
@RestController
@RequestMapping("api/channel/wx")
public class WxController {

    @Resource
    IChannelWxService iChannelWxService;

    /**
     * 开放平台渠道设置详情
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/detail")
    public AjaxResult detail() {
        ChannelWxVo vo = iChannelWxService.detail();
        return AjaxResult.success(vo);
    }

    /**
     * 开放平台渠道设置保存
     *
     * @author fzr
     * @param channelWxParam 参数
     * @return AjaxResult
     */
    @Log(title = "开放平台渠道设置保存")
    @PostMapping("/save")
    public AjaxResult save(@Validated @RequestBody ChannelWxParam channelWxParam) {
        iChannelWxService.save(channelWxParam);
        return AjaxResult.success();
    }

}
