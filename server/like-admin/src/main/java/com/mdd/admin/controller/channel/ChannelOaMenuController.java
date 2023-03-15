package com.mdd.admin.controller.channel;

import com.alibaba.fastjson2.JSONArray;
import com.mdd.admin.service.IChannelOaMenusService;
import com.mdd.common.core.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("api/channel/oaMenu")
@Api(tags = "公众号菜单管理")
public class ChannelOaMenuController {

    @Resource
    IChannelOaMenusService iChannelOaMenusService;

    @GetMapping("/detail")
    @ApiOperation(value="菜单详情")
    public AjaxResult<JSONArray> detail() {
        JSONArray detail = iChannelOaMenusService.detail();
        return AjaxResult.success(detail);
    }

    @PostMapping("/save")
    @ApiOperation(value="仅是保存菜单")
    public AjaxResult<Object> save(@RequestBody List<Object> params) {
        iChannelOaMenusService.save(params, false);
        return AjaxResult.success();
    }

    @PostMapping("/publish")
    @ApiOperation(value="保存并发布菜单")
    public AjaxResult<Object> publish(@RequestBody List<Object> params) {
        iChannelOaMenusService.save(params, true);
        return AjaxResult.success();
    }

}
