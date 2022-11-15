package com.mdd.admin.controller.channel;

import com.mdd.admin.service.IChannelOaReplyKeywordService;
import com.mdd.admin.validate.channel.ChannelRpKeywordValidate;
import com.mdd.admin.validate.commons.IdValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.channel.ChannelRpKeywordVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 公众号关键词回复管理
 */
@RestController
@RequestMapping("api/channel/oaReplyKeyword")
public class ChannelOaReplyKeywordController {

    @Resource
    IChannelOaReplyKeywordService iChannelOaReplyKeywordService;

    /**
     * 关键词回复列表
     *
     * @author fzr
     * @return AjaxResult<PageResult<ChannelRpKeywordVo>>
     */
    @GetMapping("/list")
    public AjaxResult<PageResult<ChannelRpKeywordVo>> list(@Validated PageValidate pageValidate) {
        PageResult<ChannelRpKeywordVo> list = iChannelOaReplyKeywordService.list(pageValidate);
        return AjaxResult.success(list);
    }

    /**
     * 关键词回复详情
     *
     * @author fzr
     * @param id 主键
     * @return AjaxResult<ChannelRpKeywordVo>
     */
    @GetMapping("/detail")
    public AjaxResult<ChannelRpKeywordVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        ChannelRpKeywordVo vo = iChannelOaReplyKeywordService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 关键词回复新增
     *
     * @author fzr
     * @param keywordValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/add")
    public AjaxResult<Object> add(@Validated @RequestBody ChannelRpKeywordValidate keywordValidate) {
        iChannelOaReplyKeywordService.add(keywordValidate);
        return AjaxResult.success();
    }

    /**
     * 关键词回复编辑
     *
     * @author fzr
     * @param keywordValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/edit")
    public AjaxResult<Object> edit(@Validated @RequestBody ChannelRpKeywordValidate keywordValidate) {
        iChannelOaReplyKeywordService.edit(keywordValidate);
        return AjaxResult.success();
    }

    /**
     * 关键词回复删除
     *
     * @author fzr
     * @param idValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/del")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iChannelOaReplyKeywordService.del(idValidate.getId());
        return AjaxResult.success();
    }

    /**
     * 关键词回复状态
     *
     * @author fzr
     * @param idValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/status")
    public AjaxResult<Object> status(@Validated @RequestBody IdValidate idValidate) {
        iChannelOaReplyKeywordService.status(idValidate.getId());
        return AjaxResult.success();
    }


}
