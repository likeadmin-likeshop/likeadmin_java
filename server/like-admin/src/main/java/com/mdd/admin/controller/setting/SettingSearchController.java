package com.mdd.admin.controller.setting;

import com.mdd.admin.aop.Log;
import com.mdd.admin.service.ISettingSearchService;
import com.mdd.admin.validate.setting.SettingSearchValidate;
import com.mdd.admin.vo.setting.SettingSearchDetailVo;
import com.mdd.common.core.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("api/setting/search")
@Api(tags = "配置热门搜索")
public class SettingSearchController {

    @Resource
    ISettingSearchService iSettingSearchService;

    @GetMapping("/detail")
    @ApiOperation(value="热门搜索详情")
    public AjaxResult<SettingSearchDetailVo> detail() {
        SettingSearchDetailVo vo = iSettingSearchService.detail();
        return AjaxResult.success(vo);
    }

    @Log(title = "热门搜索编辑")
    @PostMapping("/save")
    @ApiOperation(value="热门搜索编辑")
    public AjaxResult<Object> save(@Validated @RequestBody SettingSearchValidate searchValidate) {
        iSettingSearchService.save(searchValidate);
        return AjaxResult.success();
    }

}
