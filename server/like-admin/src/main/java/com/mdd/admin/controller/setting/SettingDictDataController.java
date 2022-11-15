package com.mdd.admin.controller.setting;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.admin.service.ISettingDictDataService;
import com.mdd.admin.validate.commons.IdsValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.setting.DictDataCreateValidate;
import com.mdd.admin.validate.setting.DictDataUpdateValidate;
import com.mdd.admin.vo.setting.SettingDictDataVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.utils.StringUtil;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 字典数据配置管理
 */
@RestController
@RequestMapping("api/setting/dict/data")
public class SettingDictDataController {

    @Resource
    ISettingDictDataService iSettingDictDataService;

    /**
     * 字典数据所有
     *
     * @author fzr
     * @param params 参数
     * @return jaxResult<List<SettingDictDataVo>>
     */
    @GetMapping("/all")
    public AjaxResult<List<SettingDictDataVo>> all(@RequestParam Map<String, String> params) {
        Assert.isFalse(StringUtil.isEmpty(params.get("dictType")), "dictType缺失");
        List<SettingDictDataVo> list = iSettingDictDataService.all(params);
        return AjaxResult.success(list);
    }

    /**
     * 字典数据列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param params 搜索参数
     * @return PageResult<SettingDictDataVo>
     */
    @GetMapping("/list")
    public AjaxResult<PageResult<SettingDictDataVo>> list(@Validated PageValidate pageValidate,
                                                          @RequestParam Map<String, String> params) {
        Assert.isFalse(StringUtil.isEmpty(params.get("dictType")), "dictType缺失");
        PageResult<SettingDictDataVo> list = iSettingDictDataService.list(pageValidate, params);
        return AjaxResult.success(list);
    }

    /**
     * 字典数据详情
     *
     * @author fzr
     * @param id 主键
     * @return AjaxResult<SettingDictDataVo>
     */
    @GetMapping("/detail")
    public AjaxResult<SettingDictDataVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SettingDictDataVo vo = iSettingDictDataService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 字典数据新增
     *
     * @author fzr
     * @param createValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/add")
    public AjaxResult<Object> add(@Validated @RequestBody DictDataCreateValidate createValidate) {
        iSettingDictDataService.add(createValidate);
        return AjaxResult.success();
    }

    /**
     * 字典数据编辑
     *
     * @author fzr
     * @param updateValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/edit")
    public AjaxResult<Object> edit(@Validated @RequestBody DictDataUpdateValidate updateValidate) {
        iSettingDictDataService.edit(updateValidate);
        return AjaxResult.success();
    }

    /**
     * 字典数据删除
     *
     * @author fzr
     * @param idsValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/del")
    public AjaxResult<Object> del(@Validated @RequestBody IdsValidate idsValidate) {
        iSettingDictDataService.del(idsValidate.getIds());
        return AjaxResult.success();
    }

}
