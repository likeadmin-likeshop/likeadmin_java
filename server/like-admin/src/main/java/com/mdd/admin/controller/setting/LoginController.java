package com.mdd.admin.controller.setting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("settingLoginController")
@RequestMapping("api/setting/login")
public class LoginController {

    @GetMapping("/detail")
    public Object detail() {
        return null;
    }

    @PostMapping("/save")
    public Object save() {
        return null;
    }

}
