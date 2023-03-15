package com.mdd.admin.controller.channel;

import com.mdd.admin.service.IChannelOaReplyKeywordService;
import com.mdd.admin.validate.channel.ChannelRpKeywordValidate;
import com.mdd.admin.validate.commons.IdValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.channel.ChannelRpKeywordVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/channel/oaReplyKeyword")
@Api(tags = "公众号关键回复")
public class ChannelOaReplyKeywordController {

    @Resource
    IChannelOaReplyKeywordService iChannelOaReplyKeywordService;

    @GetMapping("/list")
    @ApiOperation(value="关键词回复列表")
    public AjaxResult<PageResult<ChannelRpKeywordVo>> list(@Validated PageValidate pageValidate) {
        PageResult<ChannelRpKeywordVo> list = iChannelOaReplyKeywordService.list(pageValidate);
        return AjaxResult.success(list);
    }

    @GetMapping("/detail")
    @ApiOperation(value="关键词回复详情")
    public AjaxResult<ChannelRpKeywordVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        ChannelRpKeywordVo vo = iChannelOaReplyKeywordService.detail(id);
        return AjaxResult.success(vo);
    }

    @PostMapping("/add")
    @ApiOperation(value="关键词回复新增")
    public AjaxResult<Object> add(@Validated @RequestBody ChannelRpKeywordValidate keywordValidate) {
        iChannelOaReplyKeywordService.add(keywordValidate);
        return AjaxResult.success();
    }

    @PostMapping("/edit")
    @ApiOperation(value="关键词回复编辑")
    public AjaxResult<Object> edit(@Validated @RequestBody ChannelRpKeywordValidate keywordValidate) {
        iChannelOaReplyKeywordService.edit(keywordValidate);
        return AjaxResult.success();
    }

    @PostMapping("/del")
    @ApiOperation(value="关键词回复删除")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iChannelOaReplyKeywordService.del(idValidate.getId());
        return AjaxResult.success();
    }

    @PostMapping("/status")
    @ApiOperation(value="关键词回复状态")
    public AjaxResult<Object> status(@Validated @RequestBody IdValidate idValidate) {
        iChannelOaReplyKeywordService.status(idValidate.getId());
        return AjaxResult.success();
    }

}
