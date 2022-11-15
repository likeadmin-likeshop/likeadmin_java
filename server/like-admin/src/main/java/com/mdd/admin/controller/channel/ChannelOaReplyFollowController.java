package com.mdd.admin.controller.channel;

import com.mdd.admin.service.IChannelOaReplyFollowService;
import com.mdd.admin.validate.channel.ChannelRpFollowsValidate;
import com.mdd.admin.validate.commons.IdValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.channel.ChannelRpFollowsVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 公众号关注回复管理
 */
@RestController
@RequestMapping("api/channel/oaReplyFollow")
public class ChannelOaReplyFollowController {

    @Resource
    IChannelOaReplyFollowService iChannelOaReplyFollowService;

    /**
     * 关注回复列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @return AjaxResult<PageResult<ChannelRpFollowsVo>>
     */
    @GetMapping("/list")
    public AjaxResult<PageResult<ChannelRpFollowsVo>> list(@Validated PageValidate pageValidate) {
        PageResult<ChannelRpFollowsVo> list = iChannelOaReplyFollowService.list(pageValidate);
        return AjaxResult.success(list);
    }

    /**
     * 关注回复详情
     *
     * @author fzr
     * @param id 参数
     * @return AjaxResult<ChannelRpFollowsVo>
     */
    @GetMapping("/detail")
    public AjaxResult<ChannelRpFollowsVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        ChannelRpFollowsVo vo = iChannelOaReplyFollowService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 关注回复新增
     *
     * @author fzr
     * @param followsValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/add")
    public AjaxResult<Object> add(@Validated @RequestBody ChannelRpFollowsValidate followsValidate) {
        iChannelOaReplyFollowService.add(followsValidate);
        return AjaxResult.success();
    }

    /**
     * 关注回复编辑
     *
     * @author fzr
     * @param followsValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/edit")
    public AjaxResult<Object> edit(@Validated @RequestBody ChannelRpFollowsValidate followsValidate) {
        iChannelOaReplyFollowService.edit(followsValidate);
        return AjaxResult.success();
    }

    /**
     * 关注回复删除
     *
     * @author fzr
     * @param idValidate 参数
     * @return AjaxResult
     */
    @PostMapping("/del")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iChannelOaReplyFollowService.del(idValidate.getId());
        return AjaxResult.success();
    }

    /**
     * 关注回复状态
     *
     * @author fzr
     * @param idValidate 参数
     * @return AjaxResult
     */
    @PostMapping("/status")
    public AjaxResult<Object> status(@Validated @RequestBody IdValidate idValidate) {
        iChannelOaReplyFollowService.status(idValidate.getId());
        return AjaxResult.success();
    }

}
