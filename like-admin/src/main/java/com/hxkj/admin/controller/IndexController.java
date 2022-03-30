package com.hxkj.admin.controller;

import com.hxkj.common.utils.YmlUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {


    @GetMapping("/user/add")
    public void index() {
        System.out.println(YmlUtil.get("server.port"));


    }

    @GetMapping("/user/update")
    public void ee() {
        System.out.println("小红");
    }

}
