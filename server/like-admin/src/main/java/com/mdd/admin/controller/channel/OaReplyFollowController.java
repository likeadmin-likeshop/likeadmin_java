package com.mdd.admin.controller.channel;

import com.mdd.admin.service.channel.IChannelOaReplyFollowService;
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

@RestController
@RequestMapping("api/channel/oaReplyFollow")
public class OaReplyFollowController {

    @Resource
    IChannelOaReplyFollowService iChannelOaReplyFollowService;

    /**
     * 关注回复列表
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/list")
    public AjaxResult list(@Validated PageParam pageParam) {
        PageResult<ChannelOaReplyVo> list = iChannelOaReplyFollowService.list(pageParam);
        return AjaxResult.success(list);
    }

    /**
     * 关注回复详情
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/detail")
    public AjaxResult detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        ChannelOaReplyVo vo = iChannelOaReplyFollowService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 关注回复新增
     *
     * @author fzr
     * @param channelOaReplyParam 参数
     * @return AjaxResult
     */
    @PostMapping("/add")
    public AjaxResult add(@Validated(value = ChannelOaReplyParam.follow.class) @RequestBody ChannelOaReplyParam channelOaReplyParam) {
        iChannelOaReplyFollowService.add(channelOaReplyParam);
        return AjaxResult.success();
    }

    /**
     * 关注回复编辑
     *
     * @author fzr
     * @return AjaxResult
     */
    @PostMapping("/edit")
    public AjaxResult edit(@Validated(value = ChannelOaReplyParam.follow.class) @RequestBody ChannelOaReplyParam channelOaReplyParam) {
        iChannelOaReplyFollowService.edit(channelOaReplyParam);
        return AjaxResult.success();
    }

    /**
     * 关注回复删除
     *
     * @author fzr
     * @return AjaxResult
     */
    @PostMapping("/del")
    public AjaxResult del(@Validated @RequestBody IDParam idParam) {
        iChannelOaReplyFollowService.del(idParam.getId());
        return AjaxResult.success();
    }

    /**
     * 关注回复状态
     *
     * @author fzr
     * @return AjaxResult
     */
    @PostMapping("/status")
    public AjaxResult status(@Validated @RequestBody IDParam idParam) {
        iChannelOaReplyFollowService.status(idParam.getId());
        return AjaxResult.success();
    }

}
