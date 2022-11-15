package com.mdd.admin.controller.channel;

import com.mdd.admin.service.IChannelOaReplyDefaultService;
import com.mdd.admin.validate.channel.ChannelRpDefaultValidate;
import com.mdd.admin.validate.commons.IdValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.channel.ChannelRpDefaultVo;
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
public class ChannelOaReplyDefaultController {

    @Resource
    IChannelOaReplyDefaultService iChannelOaReplyDefaultService;

    /**
     * 默认回复列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @return AjaxResult<PageResult<ChannelRpDefaultVo>>
     */
    @GetMapping("/list")
    public AjaxResult<PageResult<ChannelRpDefaultVo>> list(@Validated PageValidate pageValidate) {
        PageResult<ChannelRpDefaultVo> list = iChannelOaReplyDefaultService.list(pageValidate);
        return AjaxResult.success(list);
    }

    /**
     * 默认回复详情
     *
     * @author fzr
     * @param id 主键
     * @return AjaxResult<ChannelRpDefaultVo>
     */
    @GetMapping("/detail")
    public AjaxResult<ChannelRpDefaultVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        ChannelRpDefaultVo vo = iChannelOaReplyDefaultService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 默认回复新增
     *
     * @author fzr
     * @param defaultValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/add")
    public AjaxResult<Object> add(@Validated @RequestBody ChannelRpDefaultValidate defaultValidate) {
        iChannelOaReplyDefaultService.add(defaultValidate);
        return AjaxResult.success();
    }

    /**
     * 默认回复编辑
     *
     * @author fzr
     * @param defaultValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/edit")
    public AjaxResult<Object> edit(@Validated @RequestBody ChannelRpDefaultValidate defaultValidate) {
        iChannelOaReplyDefaultService.edit(defaultValidate);
        return AjaxResult.success();
    }

    /**
     * 默认回复删除
     *
     * @author fzr
     * @param idValidate 参数
     * @return AjaxResult
     */
    @PostMapping("/del")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iChannelOaReplyDefaultService.del(idValidate.getId());
        return AjaxResult.success();
    }

    /**
     * 默认回复状态
     *
     * @author fzr
     * @param idValidate 参数
     * @return AjaxResult
     */
    @PostMapping("/status")
    public AjaxResult<Object> status(@Validated @RequestBody IdValidate idValidate) {
        iChannelOaReplyDefaultService.status(idValidate.getId());
        return AjaxResult.success();
    }

}
