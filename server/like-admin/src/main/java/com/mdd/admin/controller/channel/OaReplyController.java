package com.mdd.admin.controller.channel;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.admin.service.channel.IChannelOaReplyService;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.validator.IDMustValidator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 公众号回复管理
 */
@RestController
@RequestMapping("api/channel/oaReply")
public class OaReplyController {

    @Resource
    IChannelOaReplyService iChannelOaReplyService;

    /**
     * 回复列表
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/list")
    public Object list() {
        return AjaxResult.success();
    }

    /**
     * 回复详情
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail() {
        return AjaxResult.success();
    }

    /**
     * 回复新增
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @PostMapping("/add")
    public Object add(@RequestBody Map<String, String> params) {
        iChannelOaReplyService.add(params);
        return AjaxResult.success();
    }

    /**
     * 回复编辑
     *
     * @author fzr
     * @return Object
     */
    @PostMapping("/edit")
    public Object edit(@RequestBody Map<String, String> params) {
        iChannelOaReplyService.edit(params);
        return AjaxResult.success();
    }

    /**
     * 回复删除
     *
     * @author fzr
     * @return Object
     */
    @PostMapping("/del")
    public Object del(@RequestBody Map<String, String> params) {
        Assert.notNull(params.get("id"), "id参数缺失");
        iChannelOaReplyService.del(Integer.parseInt(params.get("id")));
        return AjaxResult.success();
    }

}
