package com.mdd.admin.controller.channel;

import com.mdd.admin.service.channel.IChannelOaReplyService;
import com.mdd.common.core.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 公众号回复管理
 */
@RestController
@RequestMapping("api/channel/OaReply")
public class OaReplyController {

    @Resource
    IChannelOaReplyService iChannelOaReplyService;

    @GetMapping("/list")
    public Object list() {
        return AjaxResult.success();
    }

    @GetMapping("/detail")
    public Object detail() {
        return AjaxResult.success();
    }

    @PostMapping("/add")
    public Object add(@RequestBody Map<String, String> params) {
        iChannelOaReplyService.add(params);
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
