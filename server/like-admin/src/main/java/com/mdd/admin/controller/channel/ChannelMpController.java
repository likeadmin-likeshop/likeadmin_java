package com.mdd.admin.controller.channel;

import com.mdd.admin.aop.Log;
import com.mdd.admin.service.IChannelMpConfigService;
import com.mdd.admin.validate.channel.ChannelMpValidate;
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
public class ChannelMpController {

    @Resource
    IChannelMpConfigService iChannelMpConfigService;

    /**
     * 微信小程序渠道设置详情
     *
     * @author fzr
     * @return AjaxResult<ChannelMpVo>
     */
    @GetMapping("/detail")
    public AjaxResult<ChannelMpVo> detail() {
        ChannelMpVo vo = iChannelMpConfigService.detail();
        return AjaxResult.success(vo);
    }

    /**
     * 微信小程序渠道设置保存
     *
     * @author fzr
     * @param channelMpValidate 参数
     * @return AjaxResult<Object>
     */
    @Log(title = "微信小程序渠道设置保存")
    @PostMapping("/save")
    public AjaxResult<Object> save(@Validated @RequestBody ChannelMpValidate channelMpValidate) {
        iChannelMpConfigService.save(channelMpValidate);
        return AjaxResult.success();
    }

}
