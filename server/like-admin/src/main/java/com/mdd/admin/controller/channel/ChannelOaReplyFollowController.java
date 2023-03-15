package com.mdd.admin.controller.channel;

import com.mdd.admin.service.IChannelOaReplyFollowService;
import com.mdd.admin.validate.channel.ChannelRpFollowsValidate;
import com.mdd.admin.validate.commons.IdValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.channel.ChannelRpFollowsVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/channel/oaReplyFollow")
@Api(tags = "公众号关注回复")
public class ChannelOaReplyFollowController {

    @Resource
    IChannelOaReplyFollowService iChannelOaReplyFollowService;

    @GetMapping("/list")
    @ApiOperation(value="关注回复列表")
    public AjaxResult<PageResult<ChannelRpFollowsVo>> list(@Validated PageValidate pageValidate) {
        PageResult<ChannelRpFollowsVo> list = iChannelOaReplyFollowService.list(pageValidate);
        return AjaxResult.success(list);
    }

    @GetMapping("/detail")
    @ApiOperation(value="关注回复详情")
    public AjaxResult<ChannelRpFollowsVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        ChannelRpFollowsVo vo = iChannelOaReplyFollowService.detail(id);
        return AjaxResult.success(vo);
    }

    @PostMapping("/add")
    @ApiOperation(value="关注回复新增")
    public AjaxResult<Object> add(@Validated @RequestBody ChannelRpFollowsValidate followsValidate) {
        iChannelOaReplyFollowService.add(followsValidate);
        return AjaxResult.success();
    }

    @PostMapping("/edit")
    @ApiOperation(value="关注回复编辑")
    public AjaxResult<Object> edit(@Validated @RequestBody ChannelRpFollowsValidate followsValidate) {
        iChannelOaReplyFollowService.edit(followsValidate);
        return AjaxResult.success();
    }

    @PostMapping("/del")
    @ApiOperation(value="关注回复删除")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iChannelOaReplyFollowService.del(idValidate.getId());
        return AjaxResult.success();
    }

    @PostMapping("/status")
    @ApiOperation(value="关注回复状态")
    public AjaxResult<Object> status(@Validated @RequestBody IdValidate idValidate) {
        iChannelOaReplyFollowService.status(idValidate.getId());
        return AjaxResult.success();
    }

}
