package com.hxkj.admin.controller.setting;

import com.hxkj.admin.validate.common.PageParam;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/setting/dict/type")
public class DictTypeController {

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
