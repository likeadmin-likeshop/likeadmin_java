package com.mdd.admin.controller.setting;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.admin.aop.Log;
import com.mdd.common.aop.NotPower;
import com.mdd.admin.service.ISettingDictDataService;
import com.mdd.admin.validate.commons.IdsValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.setting.DictDataCreateValidate;
import com.mdd.admin.validate.setting.DictDataUpdateValidate;
import com.mdd.admin.vo.setting.SettingDictDataVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.util.StringUtils;
import com.mdd.common.validator.annotation.IDMust;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/setting/dict/data")
@Api(tags = "配置字典数据")
public class SettingDictDataController {

    @Resource
    ISettingDictDataService iSettingDictDataService;

    @NotPower
    @GetMapping("/all")
    @ApiOperation(value="字典数据所有")
    public AjaxResult<List<SettingDictDataVo>> all(@RequestParam Map<String, String> params) {
        Assert.isFalse(StringUtils.isEmpty(params.get("dictType")), "dictType缺失");
        List<SettingDictDataVo> list = iSettingDictDataService.all(params);
        return AjaxResult.success(list);
    }

    @GetMapping("/list")
    @ApiOperation(value="字典数据列表")
    public AjaxResult<PageResult<SettingDictDataVo>> list(@Validated PageValidate pageValidate,
                                                          @RequestParam Map<String, String> params) {
        Assert.isFalse(StringUtils.isEmpty(params.get("dictType")), "dictType缺失");
        PageResult<SettingDictDataVo> list = iSettingDictDataService.list(pageValidate, params);
        return AjaxResult.success(list);
    }

    @GetMapping("/detail")
    @ApiOperation(value="字典数据详情")
    public AjaxResult<SettingDictDataVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SettingDictDataVo vo = iSettingDictDataService.detail(id);
        return AjaxResult.success(vo);
    }

    @Log(title = "字典数据新增")
    @PostMapping("/add")
    @ApiOperation(value="字典数据新增")
    public AjaxResult<Object> add(@Validated @RequestBody DictDataCreateValidate createValidate) {
        iSettingDictDataService.add(createValidate);
        return AjaxResult.success();
    }

    @Log(title = "字典数据编辑")
    @PostMapping("/edit")
    @ApiOperation(value="字典数据编辑")
    public AjaxResult<Object> edit(@Validated @RequestBody DictDataUpdateValidate updateValidate) {
        iSettingDictDataService.edit(updateValidate);
        return AjaxResult.success();
    }

    @Log(title = "字典数据删除")
    @PostMapping("/del")
    @ApiOperation(value="字典数据删除")
    public AjaxResult<Object> del(@Validated @RequestBody IdsValidate idsValidate) {
        iSettingDictDataService.del(idsValidate.getIds());
        return AjaxResult.success();
    }

}
