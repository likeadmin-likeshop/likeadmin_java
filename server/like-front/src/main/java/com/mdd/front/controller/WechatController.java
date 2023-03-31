package com.mdd.front.controller;

import com.mdd.common.aop.NotLogin;
import com.mdd.common.core.AjaxResult;
import com.mdd.front.service.IWechatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import java.util.Map;

@RestController
@RequestMapping("api/wechat")
@Api(tags = "微信管理")
public class WechatController {

    @Resource
    IWechatService iWechatService;

    @NotLogin
    @GetMapping("/jsConfig")
    @ApiOperation("微信jsConfig")
    public AjaxResult<Object> jsConfig(@Validated @NotEmpty() @RequestParam("url") String url) throws Exception {
        Map<String, Object> map = iWechatService.jsConfig(url);
        return AjaxResult.success(map);
    }

}
