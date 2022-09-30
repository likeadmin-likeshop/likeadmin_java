package com.mdd.admin.controller.common;

import com.mdd.admin.service.common.IIndexService;
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
    IIndexService iIndexService;

    /**
     * 控制台
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/console")
    public AjaxResult console() {
        Map<String, Object> map = iIndexService.console();
        return AjaxResult.success(map);
    }

    /**
     * 公共配置
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/config")
    public AjaxResult config() {
        Map<String, Object> map = iIndexService.config();
        return AjaxResult.success(map);
    }

}
