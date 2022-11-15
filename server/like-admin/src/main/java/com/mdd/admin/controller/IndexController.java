package com.mdd.admin.controller;

import com.mdd.admin.service.IIndexCommonService;
import com.mdd.common.core.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 主页管理
 */
@Slf4j
@RestController
@RequestMapping("api/common/index")
public class IndexController {

    @Resource
    IIndexCommonService iIndexCommonService;

    /**
     * 控制台
     *
     * @author fzr
     * @return AjaxResult<Map<String, Object>>
     */
    @GetMapping("/console")
    public AjaxResult<Map<String, Object>> console() {
        Map<String, Object> map = iIndexCommonService.console();
        return AjaxResult.success(map);
    }

    /**
     * 公共配置
     *
     * @author fzr
     * @return AjaxResult<Map<String, Object>
     */
    @GetMapping("/config")
    public AjaxResult<Map<String, Object>> config() {
        Map<String, Object> map = iIndexCommonService.config();
        return AjaxResult.success(map);
    }

}
