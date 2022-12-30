package com.mdd.admin.controller.setting;

import com.mdd.admin.service.ISettingNoticeService;
import com.mdd.admin.vo.setting.SettingNoticeDetailVo;
import com.mdd.admin.vo.setting.SettingNoticeListedVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 通知设置管理
 */
@RestController
@RequestMapping("api/setting/notice")
public class SettingNoticeController {

    @Resource
    ISettingNoticeService iSettingNoticeService;

    /**
     * 通知设置列表
     *
     * @author fzr
     * @param recipient 类型: 1=用户, 2=平台
     * @return AjaxResult<List<SettingNoticeListVo>>
     */
    @GetMapping("/list")
    public AjaxResult<List<SettingNoticeListedVo>> list(@RequestParam Integer recipient) {
        List<SettingNoticeListedVo> list = iSettingNoticeService.list(recipient);
        return AjaxResult.success(list);
    }

    /**
     * 通知设置详情
     *
     * @author fzr
     * @param id 主键
     * @return AjaxResult<SettingNoticeDetailVo>
     */
    @GetMapping("/detail")
    public AjaxResult<SettingNoticeDetailVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SettingNoticeDetailVo vo = iSettingNoticeService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 通知设置保存
     *
     * @author fzr
     * @param params 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/save")
    public AjaxResult<Object> save(@RequestBody Map<String, Object> params) {
        iSettingNoticeService.save(params);
        return AjaxResult.success();
    }

}
