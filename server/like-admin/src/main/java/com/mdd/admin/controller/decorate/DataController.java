package com.mdd.admin.controller.decorate;

import com.mdd.admin.service.decorate.IDecorateDataService;
import com.mdd.common.core.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 装修数据管理
 */
@RestController("decorateDataController")
@RequestMapping("api/decorate/data")
public class DataController {

    @Resource
    IDecorateDataService iDecorateDataService;

    /**
     * 获取文章数据
     *
     * @author fzr
     * @param limit 条数
     * @return AjaxResult
     */
    @GetMapping("/article")
    public AjaxResult article(@RequestParam(defaultValue = "10") Integer limit) {
        List<Map<String, Object>> list = iDecorateDataService.article(limit);
        return AjaxResult.success(list);
    }

}
