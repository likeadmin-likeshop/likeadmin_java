package com.mdd.admin.controller.channel;

import com.mdd.admin.config.aop.Log;
import com.mdd.admin.service.channel.IChannelOaService;
import com.mdd.common.core.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

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
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail() {
        Map<String, Object> map = iChannelOaService.detail();
        return AjaxResult.success(map);
    }

    /**
     * 公众号渠道设置保存
     *
     * @author fzr
     * @param param 参数
     * @return Object
     */
    @Log(title = "公众号渠道设置保存")
    @PostMapping("/save")
    public Object save(@RequestBody Map<String, String> param) {
        iChannelOaService.save(param);
        return AjaxResult.success();
    }

}
