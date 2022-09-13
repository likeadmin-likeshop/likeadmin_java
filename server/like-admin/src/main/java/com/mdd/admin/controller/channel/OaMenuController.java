package com.mdd.admin.controller.channel;

import com.mdd.admin.service.channel.IChannelOaMenuService;
import com.mdd.common.core.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 公众号菜单管理
 */
@RestController
@RequestMapping("api/channel/oa/menu")
public class OaMenuController {

    @Resource
    IChannelOaMenuService iChannelOaMenuService;

    @GetMapping("/list")
    public Object list() {
        iChannelOaMenuService.list();
        return AjaxResult.success();
    }

    @PostMapping("/add")
    public Object add() {
        return AjaxResult.success();
    }

    @PostMapping("/del")
    public Object del() {
        return AjaxResult.success();
    }

}
