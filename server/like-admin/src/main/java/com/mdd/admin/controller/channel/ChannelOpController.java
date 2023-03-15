package com.mdd.admin.controller.channel;

import com.mdd.admin.service.IChannelOpService;
import com.mdd.admin.validate.channel.ChannelOpValidate;
import com.mdd.admin.vo.channel.ChannelOpVo;
import com.mdd.common.core.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/channel/op")
@Api(tags = "微信开放渠道")
public class ChannelOpController {

    @Resource
    IChannelOpService iChannelOpService;

    @GetMapping("/detail")
    @ApiOperation(value="开放平台设置详情")
    public AjaxResult<Object> detail() {
        ChannelOpVo vo = iChannelOpService.detail();
        return AjaxResult.success(vo);
    }

    @PostMapping("/save")
    @ApiOperation(value="开放平台设置保存")
    public AjaxResult<Object> save(@Validated @RequestBody ChannelOpValidate opValidate) {
        iChannelOpService.save(opValidate);
        return AjaxResult.success();
    }

}
