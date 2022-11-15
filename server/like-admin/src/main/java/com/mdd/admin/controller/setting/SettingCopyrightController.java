package com.mdd.admin.controller.setting;

import com.mdd.admin.service.ISettingCopyrightService;
import com.mdd.admin.validate.setting.SettingCopyrightValidate;
import com.mdd.admin.vo.setting.SettingCopyrightVo;
import com.mdd.common.core.AjaxResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 网站版权配置管理
 */
@RestController
@RequestMapping("api/setting/copyright")
public class SettingCopyrightController {

    @Resource
    ISettingCopyrightService iSettingCopyrightService;

    /**
     * 获取网站版权信息
     *
     * @author fzr
     * @return AjaxResult<List<SettingCopyrightVo>>
     */
    @GetMapping("/detail")
    public AjaxResult<List<SettingCopyrightVo>> detail() {
        List<SettingCopyrightVo> list = iSettingCopyrightService.detail();
        return AjaxResult.success(list);
    }

    /**
     * 保存网站版本信息
     *
     * @author fzr
     * @param copyrightValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/save")
    public AjaxResult<Object> save(@Validated @RequestBody SettingCopyrightValidate copyrightValidate) {
        iSettingCopyrightService.save(copyrightValidate);
        return AjaxResult.success();
    }

}
