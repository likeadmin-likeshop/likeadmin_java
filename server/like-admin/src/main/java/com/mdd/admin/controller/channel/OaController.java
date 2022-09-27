package com.mdd.admin.controller.channel;

import com.mdd.admin.config.aop.Log;
import com.mdd.admin.service.channel.IChannelOaService;
import com.mdd.admin.validate.channel.ChannelOaParam;
import com.mdd.admin.vo.channel.ChannelOaVo;
import com.mdd.common.core.AjaxResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 微信公众号渠道设置
 */
@RestController
@RequestMapping("api/channel/oa")
public class OaController {

    @Resource
    IChannelOaService iChannelOaService;

    /**
     * 公众号渠道设置详情
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/detail")
    public AjaxResult detail() {
        ChannelOaVo vo = iChannelOaService.detail();
        return AjaxResult.success(vo);
    }

    /**
     * 公众号渠道设置保存
     *
     * @author fzr
     * @param channelOaParam 参数
     * @return AjaxResult
     */
    @Log(title = "公众号渠道设置保存")
    @PostMapping("/save")
    public AjaxResult save(@Validated @RequestBody ChannelOaParam channelOaParam) {
        iChannelOaService.save(channelOaParam);
        return AjaxResult.success();
    }

}
