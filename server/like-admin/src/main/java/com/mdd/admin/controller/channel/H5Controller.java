package com.mdd.admin.controller.channel;

import com.mdd.admin.config.aop.Log;
import com.mdd.admin.service.channel.IChannelH5Service;
import com.mdd.admin.validate.channel.ChannelH5Param;
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
public class H5Controller {

    @Resource
    IChannelH5Service iChannelH5Service;

    /**
     * H5渠道设置详情
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/detail")
    public AjaxResult detail() {
        ChannelH5Vo vo = iChannelH5Service.detail();
        return AjaxResult.success(vo);
    }

    /**
     * H5渠道设置保存
     *
     * @author fzr
     * @param channelH5Param 参数
     * @return AjaxResult
     */
    @Log(title = "H5渠道设置保存")
    @PostMapping("/save")
    public AjaxResult save(@Validated @RequestBody ChannelH5Param channelH5Param) {
        iChannelH5Service.save(channelH5Param);
        return AjaxResult.success();
    }

}
