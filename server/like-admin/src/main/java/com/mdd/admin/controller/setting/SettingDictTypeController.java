package com.mdd.admin.controller.setting;

import com.mdd.common.aop.NotPower;
import com.mdd.admin.service.ISettingDictTypeService;
import com.mdd.admin.validate.commons.IdsValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.setting.DictTypeCreateValidate;
import com.mdd.admin.validate.setting.DictTypeUpdateValidate;
import com.mdd.admin.vo.setting.SettingDictTypeVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 字典类型配置管理
 */
@RestController
@RequestMapping("api/setting/dict/type")
public class SettingDictTypeController {

    @Resource
    ISettingDictTypeService iSettingDictTypeService;

    /**
     * 字典类型所有
     *
     * @author fzr
     * @return AjaxResult<List<SettingDictTypeVo>>
     */
    @NotPower
    @GetMapping("/all")
    public AjaxResult<List<SettingDictTypeVo>> all() {
        List<SettingDictTypeVo> list = iSettingDictTypeService.all();
        return AjaxResult.success(list);
    }

    /**
     * 字典类型列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param params 搜索参数
     * @return AjaxResult<PageResult<SettingDictTypeVo>>
     */
    @GetMapping("/list")
    public AjaxResult<PageResult<SettingDictTypeVo>> list(@Validated PageValidate pageValidate,
                       @RequestParam Map<String, String> params) {
        PageResult<SettingDictTypeVo> list = iSettingDictTypeService.list(pageValidate, params);
        return AjaxResult.success(list);
    }

    /**
     * 字典类型详情
     *
     * @author fzr
     * @param id 主键
     * @return AjaxResult<SettingDictTypeVo>
     */
    @GetMapping("/detail")
    public AjaxResult<SettingDictTypeVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SettingDictTypeVo vo = iSettingDictTypeService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 字典类型新增
     *
     * @author fzr
     * @param createValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/add")
    public AjaxResult<Object> add(@Validated @RequestBody DictTypeCreateValidate createValidate) {
        iSettingDictTypeService.add(createValidate);
        return AjaxResult.success();
    }

    /**
     * 字典类型编辑
     *
     * @author fzr
     * @param updateValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/edit")
    public AjaxResult<Object> edit(@Validated @RequestBody DictTypeUpdateValidate updateValidate) {
        iSettingDictTypeService.edit(updateValidate);
        return AjaxResult.success();
    }

    /**
     * 字典类型删除
     *
     * @author fzr
     * @param idsValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/del")
    public AjaxResult<Object> del(@Validated @RequestBody IdsValidate idsValidate) {
        iSettingDictTypeService.del(idsValidate.getIds());
        return AjaxResult.success();
    }

}
