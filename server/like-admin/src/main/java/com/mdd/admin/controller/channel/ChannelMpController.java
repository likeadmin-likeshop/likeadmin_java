package com.mdd.admin.controller.channel;

import com.mdd.admin.aop.Log;
import com.mdd.admin.service.IChannelMpConfigService;
import com.mdd.admin.validate.channel.ChannelMpValidate;
import com.mdd.admin.vo.channel.ChannelMpVo;
import com.mdd.common.core.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/channel/mp")
@Api(tags = "微信程序渠道")
public class ChannelMpController {

    @Resource
    IChannelMpConfigService iChannelMpConfigService;

    @GetMapping("/detail")
    @ApiOperation(value="微信程序渠道设置详情")
    public AjaxResult<ChannelMpVo> detail() {
        ChannelMpVo vo = iChannelMpConfigService.detail();
        return AjaxResult.success(vo);
    }

    @Log(title = "微信小程序渠道设置保存")
    @PostMapping("/save")
    @ApiOperation(value="微信程序渠道设置保存")
    public AjaxResult<Object> save(@Validated @RequestBody ChannelMpValidate channelMpValidate) {
        iChannelMpConfigService.save(channelMpValidate);
        return AjaxResult.success();
    }

}
