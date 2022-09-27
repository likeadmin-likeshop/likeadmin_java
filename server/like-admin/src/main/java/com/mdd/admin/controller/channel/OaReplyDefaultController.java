package com.mdd.admin.controller.channel;

import com.mdd.admin.service.channel.IChannelOaReplyDefaultService;
import com.mdd.admin.validate.channel.ChannelOaReplyParam;
import com.mdd.admin.validate.common.IDParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.vo.channel.ChannelOaReplyVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 公众号默认回复管理
 */
@RestController
@RequestMapping("api/channel/oaReplyDefault")
public class OaReplyDefaultController {

    @Resource
    IChannelOaReplyDefaultService iChannelOaReplyDefaultService;

    /**
     * 默认回复列表
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/list")
    public AjaxResult list(@Validated PageParam pageParam) {
        PageResult<ChannelOaReplyVo> list = iChannelOaReplyDefaultService.list(pageParam);
        return AjaxResult.success(list);
    }

    /**
     * 回复详情
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/detail")
    public AjaxResult detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        ChannelOaReplyVo vo = iChannelOaReplyDefaultService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 默认回复新增
     *
     * @author fzr
     * @param channelOaReplyParam 参数
     * @return AjaxResult
     */
    @PostMapping("/add")
    public AjaxResult add(@Validated(value = ChannelOaReplyParam.defaults.class) @RequestBody ChannelOaReplyParam channelOaReplyParam) {
        iChannelOaReplyDefaultService.add(channelOaReplyParam);
        return AjaxResult.success();
    }

    /**
     * 默认回复编辑
     *
     * @author fzr
     * @return AjaxResult
     */
    @PostMapping("/edit")
    public AjaxResult edit(@Validated(value = ChannelOaReplyParam.defaults.class) @RequestBody ChannelOaReplyParam channelOaReplyParam) {
        iChannelOaReplyDefaultService.edit(channelOaReplyParam);
        return AjaxResult.success();
    }

    /**
     * 默认回复删除
     *
     * @author fzr
     * @return AjaxResult
     */
    @PostMapping("/del")
    public AjaxResult del(@Validated @RequestBody IDParam idParam) {
        iChannelOaReplyDefaultService.del(idParam.getId());
        return AjaxResult.success();
    }

    /**
     * 默认回复状态
     *
     * @author fzr
     * @return AjaxResult
     */
    @PostMapping("/status")
    public AjaxResult status(@Validated @RequestBody IDParam idParam) {
        iChannelOaReplyDefaultService.status(idParam.getId());
        return AjaxResult.success();
    }

}
