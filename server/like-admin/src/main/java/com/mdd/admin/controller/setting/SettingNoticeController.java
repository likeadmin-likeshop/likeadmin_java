package com.mdd.admin.controller.setting;

import com.mdd.admin.aop.Log;
import com.mdd.admin.service.ISettingNoticeService;
import com.mdd.admin.vo.setting.SettingNoticeDetailVo;
import com.mdd.admin.vo.setting.SettingNoticeListedVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.validator.annotation.IDMust;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/setting/notice")
@Api(tags = "配置消息通知")
public class SettingNoticeController {

    @Resource
    ISettingNoticeService iSettingNoticeService;

    @GetMapping("/list")
    @ApiOperation(value="通知设置列表")
    public AjaxResult<List<SettingNoticeListedVo>> list(@RequestParam Integer recipient) {
        List<SettingNoticeListedVo> list = iSettingNoticeService.list(recipient);
        return AjaxResult.success(list);
    }

    @GetMapping("/detail")
    @ApiOperation(value="通知设置详情")
    public AjaxResult<SettingNoticeDetailVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SettingNoticeDetailVo vo = iSettingNoticeService.detail(id);
        return AjaxResult.success(vo);
    }

    @Log(title = "通知设置编辑")
    @PostMapping("/save")
    @ApiOperation(value="通知设置编辑")
    public AjaxResult<Object> save(@RequestBody Map<String, Object> params) {
        iSettingNoticeService.save(params);
        return AjaxResult.success();
    }

}
