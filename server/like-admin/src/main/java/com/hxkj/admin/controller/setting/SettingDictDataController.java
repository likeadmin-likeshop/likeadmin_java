package com.hxkj.admin.controller.setting;

import com.hxkj.common.core.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/setting/dict/data")
public class SettingDictDataController {

    @GetMapping("/list")
    public Object list() {
        return AjaxResult.success();
    }

    @GetMapping("/detail")
    public Object detail() {
        return AjaxResult.success();
    }

    @PostMapping("/add")
    public Object add() {
        return AjaxResult.success();
    }

    @PostMapping("/edit")
    public Object edit() {
        return AjaxResult.success();
    }

    @PostMapping("/del")
    public Object del() {
        return AjaxResult.success();
    }

}
