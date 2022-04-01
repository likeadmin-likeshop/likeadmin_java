package com.hxkj.admin.controller;

import com.hxkj.admin.service.IIndexService;
import com.hxkj.common.core.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 主页管理
 */
@Slf4j
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
        Map<String, Object> map = iIndexService.console();
        return AjaxResult.success(map);
    }

}
