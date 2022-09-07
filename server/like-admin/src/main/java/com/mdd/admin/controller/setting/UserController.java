package com.mdd.admin.controller.setting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户设置管理
 */
@RestController("settingUserController")
@RequestMapping("api/setting/user")
public class UserController {

    @GetMapping("/detail")
    public Object detail() {
        return null;
    }

    @PostMapping("/save")
    public Object save() {
        return null;
    }

}
