package com.mdd.admin.controller.setting;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.admin.aop.Log;
import com.mdd.admin.service.ISettingStorageService;
import com.mdd.common.core.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/setting/storage")
@Api(tags = "配置存储方式")
public class SettingStorageController {

    @Resource
    ISettingStorageService iSettingStorageService;

    @GetMapping("/list")
    @ApiOperation(value="存储列表")
    public AjaxResult<List<Map<String, Object>>> list() {
        List<Map<String, Object>> list = iSettingStorageService.list();
        return AjaxResult.success(list);
    }

    @GetMapping("/detail")
    @ApiOperation(value="存储详情")
    public AjaxResult<Map<String, Object>> detail(String alias) {
        Map<String, Object> map = iSettingStorageService.detail(alias);
        return AjaxResult.success(map);
    }

    @Log(title = "存储编辑")
    @PostMapping("/edit")
    @ApiOperation(value="存储编辑")
    public AjaxResult<Object> edit(@RequestBody Map<String, String> params) {
        iSettingStorageService.edit(params);
        return AjaxResult.success();
    }

    @Log(title = "存储切换")
    @PostMapping("/change")
    @ApiOperation(value="存储切换")
    public AjaxResult<Object> change(@RequestBody Map<String, String> params) {
        Assert.notNull(params.get("alias"), "alias参数缺失");
        Assert.notNull(params.get("status"), "status参数缺失");
        String alias = params.get("alias");
        Integer status = Integer.parseInt(params.get("status"));
        iSettingStorageService.change(alias, status);
        return AjaxResult.success();
    }

}
