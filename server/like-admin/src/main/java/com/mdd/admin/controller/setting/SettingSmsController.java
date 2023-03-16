package com.mdd.admin.controller.setting;

import com.mdd.admin.aop.Log;
import com.mdd.admin.service.ISettingSmsService;
import com.mdd.common.core.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 短信设置管理
 */
@RestController
@RequestMapping("api/setting/sms")
@Api(tags = "配置短信引擎")
public class SettingSmsController {

    @Resource
    ISettingSmsService iSettingSmsService;

    @GetMapping("/list")
    @ApiOperation(value="短信引擎列表")
    public AjaxResult<List<Map<String, Object>>> list() {
        List<Map<String, Object>> list = iSettingSmsService.list();
        return AjaxResult.success(list);
    }

    @GetMapping("/detail")
    @ApiOperation(value="短信引擎详情")
    public AjaxResult<Map<String, Object>> detail(String alias) {
        Map<String, Object> map = iSettingSmsService.detail(alias);
        return AjaxResult.success(map);
    }

    @Log(title = "短信引擎编辑")
    @PostMapping("/save")
    @ApiOperation(value="短信引擎编辑")
    public AjaxResult<Object> save(@RequestBody Map<String, String> params) {
        iSettingSmsService.save(params);
        return AjaxResult.success();
    }

}
