package com.hxkj.admin.controller.setting;

import com.hxkj.admin.service.setting.IDictDataService;
import com.hxkj.admin.validate.common.PageParam;
import com.hxkj.admin.validate.setting.SettingDictDataParam;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("api/setting/dict/data")
public class DictDataController {

    @Resource
    IDictDataService iSettingDictDataService;

    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        return AjaxResult.success();
    }

    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        return AjaxResult.success();
    }

    @PostMapping("/add")
    public Object add(@Validated(value = SettingDictDataParam.create.class)
                          @RequestBody SettingDictDataParam settingDictDataParam) {
        return AjaxResult.success();
    }

    @PostMapping("/edit")
    public Object edit(@Validated(value = SettingDictDataParam.update.class)
                           @RequestBody SettingDictDataParam settingDictDataParam) {
        return AjaxResult.success();
    }

    @PostMapping("/del")
    public Object del(@Validated(value = SettingDictDataParam.delete.class)
                          @RequestBody SettingDictDataParam settingDictDataParam) {
        return AjaxResult.success();
    }

}
