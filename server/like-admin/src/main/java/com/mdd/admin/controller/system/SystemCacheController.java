package com.mdd.admin.controller.system;


import com.mdd.admin.aop.Log;
import com.mdd.admin.service.ISystemCacheService;
import com.mdd.common.core.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/system/cache")
@Api(tags = "系统缓存管理")
public class SystemCacheController {

    @Resource
    ISystemCacheService iSystemCacheService;

    @Log(title = "清除系统缓存")
    @PostMapping("/clear")
    @ApiOperation(value = "清除系统缓存")
    public AjaxResult<Object> clear() {
        iSystemCacheService.clear();
        return AjaxResult.success();
    }


}
