package com.mdd.admin.controller.channel;

import com.mdd.admin.service.channel.IChannelOaMenuService;
import com.mdd.common.core.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公众号菜单管理
 */
@RestController
@RequestMapping("api/channel/oaMenu")
public class OaMenuController {

    @Resource
    IChannelOaMenuService iChannelOaMenuService;

    /**
     * 菜单详情
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail() {
        iChannelOaMenuService.list();
        return AjaxResult.success();
    }

    /**
     * 仅是保存菜单
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @PostMapping("/save")
    public Object save(@RequestBody List<Object> params) {
        iChannelOaMenuService.save(params, false);
        return AjaxResult.success();
    }

    /**
     * 保存并发布菜单
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @PostMapping("/publish")
    public Object publish(@RequestBody List<Object> params) {
        iChannelOaMenuService.save(params, true);
        return AjaxResult.success();
    }

}
