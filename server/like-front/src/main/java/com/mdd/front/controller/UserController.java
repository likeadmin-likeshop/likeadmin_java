package com.mdd.front.controller;

import com.mdd.common.core.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    @GetMapping("/center")
    public Object center() {
        return AjaxResult.success();
    }

    @GetMapping("/info")
    public Object info() {
        return AjaxResult.success();
    }

    @GetMapping("/agreement")
    public Object agreement() {
        return AjaxResult.success();
    }

}
