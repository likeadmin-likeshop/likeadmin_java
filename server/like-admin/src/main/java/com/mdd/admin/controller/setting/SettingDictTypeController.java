package com.mdd.admin.controller.setting;

import com.mdd.admin.aop.Log;
import com.mdd.common.aop.NotPower;
import com.mdd.admin.service.ISettingDictTypeService;
import com.mdd.admin.validate.commons.IdsValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.setting.DictTypeCreateValidate;
import com.mdd.admin.validate.setting.DictTypeUpdateValidate;
import com.mdd.admin.vo.setting.SettingDictTypeVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/setting/dict/type")
@Api(tags = "配置字典类型")
public class SettingDictTypeController {

    @Resource
    ISettingDictTypeService iSettingDictTypeService;

    @NotPower
    @GetMapping("/all")
    @ApiOperation(value="字典类型所有")
    public AjaxResult<List<SettingDictTypeVo>> all() {
        List<SettingDictTypeVo> list = iSettingDictTypeService.all();
        return AjaxResult.success(list);
    }

    @GetMapping("/list")
    @ApiOperation(value="字典类型列表")
    public AjaxResult<PageResult<SettingDictTypeVo>> list(@Validated PageValidate pageValidate,
                       @RequestParam Map<String, String> params) {
        PageResult<SettingDictTypeVo> list = iSettingDictTypeService.list(pageValidate, params);
        return AjaxResult.success(list);
    }

    @GetMapping("/detail")
    @ApiOperation(value="字典类型详情")
    public AjaxResult<SettingDictTypeVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SettingDictTypeVo vo = iSettingDictTypeService.detail(id);
        return AjaxResult.success(vo);
    }

    @Log(title = "字典类型新增")
    @PostMapping("/add")
    @ApiOperation(value="字典类型新增")
    public AjaxResult<Object> add(@Validated @RequestBody DictTypeCreateValidate createValidate) {
        iSettingDictTypeService.add(createValidate);
        return AjaxResult.success();
    }

    @Log(title = "字典类型编辑")
    @PostMapping("/edit")
    @ApiOperation(value="字典类型编辑")
    public AjaxResult<Object> edit(@Validated @RequestBody DictTypeUpdateValidate updateValidate) {
        iSettingDictTypeService.edit(updateValidate);
        return AjaxResult.success();
    }

    @Log(title = "字典类型删除")
    @PostMapping("/del")
    @ApiOperation(value="字典类型删除")
    public AjaxResult<Object> del(@Validated @RequestBody IdsValidate idsValidate) {
        iSettingDictTypeService.del(idsValidate.getIds());
        return AjaxResult.success();
    }

}
