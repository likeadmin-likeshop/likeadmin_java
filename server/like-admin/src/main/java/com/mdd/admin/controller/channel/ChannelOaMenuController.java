package com.mdd.admin.controller.channel;

import com.alibaba.fastjson2.JSONArray;
import com.mdd.admin.service.IChannelOaMenusService;
import com.mdd.common.core.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公众号菜单管理
 */
@RestController
@RequestMapping("api/channel/oaMenu")
public class ChannelOaMenuController {

    @Resource
    IChannelOaMenusService iChannelOaMenusService;

    /**
     * 菜单详情
     *
     * @author fzr
     * @return AjaxResult<JSONArray>
     */
    @GetMapping("/detail")
    public AjaxResult<JSONArray> detail() {
        JSONArray detail = iChannelOaMenusService.detail();
        return AjaxResult.success(detail);
    }

    /**
     * 仅是保存菜单
     *
     * @author fzr
     * @param params 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/save")
    public AjaxResult<Object> save(@RequestBody List<Object> params) {
        iChannelOaMenusService.save(params, false);
        return AjaxResult.success();
    }

    /**
     * 保存并发布菜单
     *
     * @author fzr
     * @param params 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/publish")
    public AjaxResult<Object> publish(@RequestBody List<Object> params) {
        iChannelOaMenusService.save(params, true);
        return AjaxResult.success();
    }

}
