package com.mdd.admin.controller.setting;

import com.mdd.admin.service.ISettingSearchService;
import com.mdd.admin.validate.setting.SettingSearchValidate;
import com.mdd.admin.vo.setting.SettingSearchDetailVo;
import com.mdd.common.core.AjaxResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 热搜设置
 */
@RestController
@RequestMapping("api/setting/search")
public class SettingSearchController {

    @Resource
    ISettingSearchService iSettingSearchService;

    /**
     * 热门搜索详情
     *
     * @author fzr
     * @return AjaxResult<SettingSearchDetailVo>
     */
    @GetMapping("/detail")
    public AjaxResult<SettingSearchDetailVo> detail() {
        SettingSearchDetailVo vo = iSettingSearchService.detail();
        return AjaxResult.success(vo);
    }

    /**
     * 热门搜索保存
     *
     * @author fzr
     * @param searchValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/save")
    public AjaxResult<Object> save(@Validated @RequestBody SettingSearchValidate searchValidate) {
        iSettingSearchService.save(searchValidate);
        return AjaxResult.success();
    }

}
