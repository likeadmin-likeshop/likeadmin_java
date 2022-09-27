package com.mdd.admin.controller.channel;

import com.mdd.admin.service.channel.IChannelOaReplyKeywordService;
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
@RequestMapping("api/channel/oaReplyKeyword")
public class OaReplyKeywordController {

    @Resource
    IChannelOaReplyKeywordService iChannelOaReplyKeywordService;

    /**
     * 关键词回复列表
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/list")
    public AjaxResult list(@Validated PageParam pageParam) {
        PageResult<ChannelOaReplyVo> list = iChannelOaReplyKeywordService.list(pageParam);
        return AjaxResult.success(list);
    }

    /**
     * 关键词回复详情
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/detail")
    public AjaxResult detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        ChannelOaReplyVo vo = iChannelOaReplyKeywordService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 关键词回复新增
     *
     * @author fzr
     * @param channelOaReplyParam 参数
     * @return AjaxResult
     */
    @PostMapping("/add")
    public AjaxResult add(@Validated(value = ChannelOaReplyParam.keywords.class) @RequestBody ChannelOaReplyParam channelOaReplyParam) {
        iChannelOaReplyKeywordService.add(channelOaReplyParam);
        return AjaxResult.success();
    }

    /**
     * 关键词回复编辑
     *
     * @author fzr
     * @return AjaxResult
     */
    @PostMapping("/edit")
    public AjaxResult edit(@Validated(value = ChannelOaReplyParam.keywords.class) @RequestBody ChannelOaReplyParam channelOaReplyParam) {
        iChannelOaReplyKeywordService.edit(channelOaReplyParam);
        return AjaxResult.success();
    }

    /**
     * 关键词回复删除
     *
     * @author fzr
     * @return AjaxResult
     */
    @PostMapping("/del")
    public AjaxResult del(@Validated @RequestBody IDParam idParam) {
        iChannelOaReplyKeywordService.del(idParam.getId());
        return AjaxResult.success();
    }

    /**
     * 关键词回复状态
     *
     * @author fzr
     * @return AjaxResult
     */
    @PostMapping("/status")
    public AjaxResult status(@Validated @RequestBody IDParam idParam) {
        iChannelOaReplyKeywordService.status(idParam.getId());
        return AjaxResult.success();
    }


}
