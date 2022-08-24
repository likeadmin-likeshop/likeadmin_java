package com.mdd.admin.controller.setting;

import com.mdd.admin.service.notice.INoticeSettingService;
import com.mdd.admin.vo.notice.NoticeSettingDetailVo;
import com.mdd.admin.vo.notice.NoticeSettingListVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.utils.ToolsUtil;
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
    INoticeSettingService iNoticeSettingService;

    /**
     * 通知设置列表
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/list")
    public Object list() {
        List<NoticeSettingListVo> list = iNoticeSettingService.list();
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
        NoticeSettingDetailVo vo = iNoticeSettingService.detail(id);
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
        System.out.println(ToolsUtil.objectToMap(params.get("systemNotice")));
        iNoticeSettingService.save(params);
        return AjaxResult.success();
    }

}
