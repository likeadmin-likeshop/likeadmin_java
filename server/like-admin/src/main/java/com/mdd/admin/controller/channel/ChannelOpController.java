package com.mdd.admin.controller.channel;

import com.mdd.admin.service.IChannelOpService;
import com.mdd.admin.validate.channel.ChannelOpValidate;
import com.mdd.admin.vo.channel.ChannelOpVo;
import com.mdd.common.core.AjaxResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 微信开发平台渠道设置
 */
@RestController
@RequestMapping("api/channel/op")
public class ChannelOpController {

    @Resource
    IChannelOpService iChannelOpService;

    /**
     * 开放平台设置详情
     *
     * @author fzr
     * @return AjaxResult<Object>
     */
    @GetMapping("/detail")
    public AjaxResult<Object> detail() {
        ChannelOpVo vo = iChannelOpService.detail();
        return AjaxResult.success(vo);
    }

    /**
     * 开放平台设置保存
     *
     * @author fzr
     * @param opValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/save")
    public AjaxResult<Object> save(@Validated @RequestBody ChannelOpValidate opValidate) {
        iChannelOpService.save(opValidate);
        return AjaxResult.success();
    }

}
