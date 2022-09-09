package com.mdd.admin.controller.setting;

import com.mdd.admin.service.setting.ISettingSearchService;
import com.mdd.common.core.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 热搜设置
 */
@RestController("settingSearchController")
@RequestMapping("api/setting/search")
public class SearchController {

    @Resource
    ISettingSearchService iSettingSearchService;

    /**
     * 热门搜索详情
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail() {
        Map<String, Object> map = iSettingSearchService.detail();
        return AjaxResult.success(map);
    }

    /**
     * 热门搜索保存
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @PostMapping("/save")
    public Object save(@RequestBody Map<String, Object> params) {
        iSettingSearchService.save(params);
        return AjaxResult.success();
    }



}
