package com.mdd.admin.controller.setting;

import com.mdd.admin.service.ISettingProtocolService;
import com.mdd.admin.validate.setting.SettingProtocolValidate;
import com.mdd.admin.vo.setting.SettingProtocolDetailVo;
import com.mdd.common.core.AjaxResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 政策协议配置管理
 */
@RestController
@RequestMapping("api/setting/protocol")
public class SettingProtocolController {

    @Resource
    ISettingProtocolService iSettingProtocolService;

    /**
     * 获取政策协议信息
     *
     * @author fzr
     * @return AjaxResult<SettingProtocolDetailVo>
     */
    @GetMapping("/detail")
    public AjaxResult<SettingProtocolDetailVo> detail() {
        SettingProtocolDetailVo detail = iSettingProtocolService.detail();
        return AjaxResult.success(detail);
    }

    /**
     * 保存政策协议信息
     *
     * @author fzr
     * @param protocolValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/save")
    public AjaxResult<Object> save(@Validated @RequestBody SettingProtocolValidate protocolValidate) {
        iSettingProtocolService.save(protocolValidate);
        return AjaxResult.success();
    }

}
