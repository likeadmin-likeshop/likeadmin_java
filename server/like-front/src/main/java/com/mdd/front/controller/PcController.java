package com.mdd.front.controller;

import com.mdd.common.core.AjaxResult;
import com.mdd.front.service.IPcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * pc端接口
 */
@RestController
@RequestMapping("/api/pc")
public class PcController {

    @Resource
    IPcService iPcService;

    @GetMapping("/index")
    public AjaxResult<Map<String,Object>> index()
    {
        Map<String, Object> index = iPcService.index();
        return AjaxResult.success(index);
    }

    /**
     * 配置
     * @author cjh
     * @return AjaxResult<Map<String, Object>>
     */
    @GetMapping("/getConfig")
    public AjaxResult<Map<String, Object>> getConfig() {
        Map<String, Object> config = iPcService.getConfig();
        return AjaxResult.success(config);
    }

}
