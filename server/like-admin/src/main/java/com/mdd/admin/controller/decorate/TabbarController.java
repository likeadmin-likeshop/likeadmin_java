package com.mdd.admin.controller.decorate;

import com.mdd.admin.service.decorate.IDecorateTabbarService;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.utils.ArrayUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 装修底部导航管理
 */
@RestController
@RequestMapping("api/decorate/tabbar")
public class TabbarController {

    @Resource
    IDecorateTabbarService iDecorateTabbarService;

    /**
     * 底部导航详情
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail() {
        Map<String, Object> detail = iDecorateTabbarService.detail();
        return AjaxResult.success(detail);
    }

    /**
     * 底部导航保存
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @PostMapping("/save")
    public Object save(@RequestBody Map<String, Object> params) {
        System.out.println(  ArrayUtil.objectToListAsStr(params.get("list")));

        iDecorateTabbarService.save(params);
        return AjaxResult.success();
    }

}
