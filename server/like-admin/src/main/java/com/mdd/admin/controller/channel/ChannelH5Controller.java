package com.mdd.admin.controller.channel;

import com.mdd.admin.aop.Log;
import com.mdd.admin.service.IChannelH5ConfigService;
import com.mdd.admin.validate.channel.ChannelH5Validate;
import com.mdd.admin.vo.channel.ChannelH5Vo;
import com.mdd.common.core.AjaxResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * H5渠道设置
 */
@RestController
@RequestMapping("api/channel/h5")
public class ChannelH5Controller {

    @Resource
    IChannelH5ConfigService iChannelH5ConfigService;

    /**
     * H5渠道设置详情
     *
     * @author fzr
     * @return AjaxResult<ChannelH5Vo>
     */
    @GetMapping("/detail")
    public AjaxResult<ChannelH5Vo> detail() {
        ChannelH5Vo vo = iChannelH5ConfigService.detail();
        return AjaxResult.success(vo);
    }

    /**
     * H5渠道设置保存
     *
     * @author fzr
     * @param channelH5Validate 参数
     * @return AjaxResult<Object>
     */
    @Log(title = "H5渠道设置保存")
    @PostMapping("/save")
    public AjaxResult<Object> save(@Validated @RequestBody ChannelH5Validate channelH5Validate) {
        iChannelH5ConfigService.save(channelH5Validate);
        return AjaxResult.success();
    }

}
