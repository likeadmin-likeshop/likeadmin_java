package com.mdd.admin.controller.setting;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.admin.service.setting.ISettingStorageService;
import com.mdd.common.core.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 存储方式配置管理
 */
@RestController("settingStorageController")
@RequestMapping("api/setting/storage")
public class StorageController {

    @Resource
    ISettingStorageService iSettingStorageService;

    /**
     * 存储列表
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/list")
    public Object list() {
        List<Map<String, Object>> list = iSettingStorageService.list();
        return AjaxResult.success(list);
    }

    /**
     * 存储详情
     *
     * @param alias 引擎别名
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(String alias) {
        Map<String, Object> map = iSettingStorageService.detail(alias);
        return AjaxResult.success(map);
    }

    /**
     * 存储编辑
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @PostMapping("/edit")
    public Object edit(@RequestBody Map<String, String> params) {
        iSettingStorageService.edit(params);
        return AjaxResult.success();
    }

    /**
     * 存储切换
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @PostMapping("/change")
    public Object change(@RequestBody Map<String, String> params) {
        Assert.notNull(params.get("alias"), "alias参数缺失");
        Assert.notNull(params.get("status"), "status参数缺失");
        String alias = params.get("alias");
        Integer status = Integer.parseInt(params.get("status"));
        iSettingStorageService.change(alias, status);
        return AjaxResult.success();
    }

}
