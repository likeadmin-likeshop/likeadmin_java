package com.hxkj.admin.controller;

import com.hxkj.admin.service.IIndexService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 主页管理
 */
@RestController
@RequestMapping("/api/index")
public class IndexController {

    @Resource
    IIndexService iIndexService;

    /**
     * 控制台
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/console")
    public Object console() {
        iIndexService.console();
        return null;
    }

}
