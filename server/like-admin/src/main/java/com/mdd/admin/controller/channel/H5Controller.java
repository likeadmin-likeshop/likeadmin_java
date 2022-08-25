package com.mdd.admin.controller.channel;

import com.mdd.admin.config.aop.Log;
import com.mdd.admin.service.channel.IChannelH5Service;
import com.mdd.common.core.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * H5渠道设置
 */
@RestController
@RequestMapping("api/channel/h5")
public class H5Controller {

    @Resource
    IChannelH5Service iChannelH5Service;

    /**
     * H5渠道配置详情
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail() {
        Map<String, Object> map = iChannelH5Service.detail();
        return AjaxResult.success(map);
    }

    /**
     * H5渠道配置保存
     *
     * @author fzr
     * @param param 参数
     * @return Object
     */
    @Log(title = "H5渠道保存")
    @PostMapping("/save")
    public Object save(@RequestBody Map<String, String> param) {
        iChannelH5Service.save(param);
        return AjaxResult.success();
    }

}
