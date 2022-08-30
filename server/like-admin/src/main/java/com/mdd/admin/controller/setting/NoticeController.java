package com.mdd.admin.controller.setting;

import com.mdd.admin.service.setting.ISettingNoticeService;
import com.mdd.admin.vo.setting.NoticeDetailVo;
import com.mdd.admin.vo.setting.NoticeListVo;
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
public class NoticeController {

    @Resource
    ISettingNoticeService iSettingNoticeService;

    /**
     * 通知设置列表
     *
     * @author fzr
     * @param recipient 类型: 1=用户, 2=平台
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@RequestParam Integer recipient) {
        System.out.println(recipient);
        List<NoticeListVo> list = iSettingNoticeService.list(recipient);
        return AjaxResult.success(list);
    }

    /**
     * 通知设置详情
     *
     * @author fzr
     * @param id 主键
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        NoticeDetailVo vo = iSettingNoticeService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 通知设置保存
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @PostMapping("/save")
    public Object save(@RequestBody Map<String, Object> params) {
        iSettingNoticeService.save(params);
        return AjaxResult.success();
    }

}
