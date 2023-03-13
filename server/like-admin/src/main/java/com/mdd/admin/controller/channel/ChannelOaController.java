package com.mdd.admin.controller.channel;

import com.mdd.admin.aop.Log;
import com.mdd.admin.service.IChannelOaConfigService;
import com.mdd.admin.validate.channel.ChannelOaValidate;
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
public class ChannelOaController {

    @Resource
    IChannelOaConfigService iChannelOaConfigService;

    /**
     * 公众号渠道设置详情
     *
     * @author fzr
     * @return AjaxResult<ChannelOaVo>
     */
    @GetMapping("/detail")
    public AjaxResult<ChannelOaVo> detail() {
        ChannelOaVo vo = iChannelOaConfigService.detail();
        return AjaxResult.success(vo);
    }

    /**
     * 公众号渠道设置保存
     *
     * @author fzr
     * @param channelOaValidate 参数
     * @return AjaxResult<Object>
     */
    @Log(title = "公众号渠道设置保存")
    @PostMapping("/save")
    public AjaxResult<Object> save(@Validated @RequestBody ChannelOaValidate channelOaValidate) {
        iChannelOaConfigService.save(channelOaValidate);
        return AjaxResult.success();
    }

}
