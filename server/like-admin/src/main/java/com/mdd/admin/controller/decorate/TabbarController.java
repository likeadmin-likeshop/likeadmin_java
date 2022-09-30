package com.mdd.admin.controller.decorate;

import com.mdd.admin.service.decorate.IDecorateTabbarService;
import com.mdd.admin.vo.decorate.DecorateTabbarVo;
import com.mdd.common.core.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 装修底部导航管理
 */
@RestController("decorateTabbarController")
@RequestMapping("api/decorate/tabbar")
public class TabbarController {

    @Resource
    IDecorateTabbarService iDecorateTabbarService;

    /**
     * 底部导航详情
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/detail")
    public AjaxResult detail() {
        DecorateTabbarVo vo = iDecorateTabbarService.detail();
        return AjaxResult.success(vo);
    }

    /**
     * 底部导航保存
     *
     * @author fzr
     * @param params 参数
     * @return AjaxResult
     */
    @PostMapping("/save")
    public AjaxResult save(@RequestBody Map<String, Object> params) {
        iDecorateTabbarService.save(params);
        return AjaxResult.success();
    }

}
