package com.mdd.admin.controller.setting;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.admin.service.setting.ISettingDictDataService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.validate.setting.DictDataParam;
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
@RestController("settingDictDataController")
@RequestMapping("api/setting/dict/data")
public class DictDataController {

    @Resource
    ISettingDictDataService iSettingDictDataService;

    /**
     * 字典数据所有
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/all")
    public AjaxResult all(@RequestParam Map<String, String> params) {
        Assert.isFalse(StringUtil.isEmpty(params.get("dictType")), "dictType缺失");
        List<SettingDictDataVo> list = iSettingDictDataService.all(params);
        return AjaxResult.success(list);
    }

    /**
     * 字典数据列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return AjaxResult
     */
    @GetMapping("/list")
    public AjaxResult list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        Assert.isFalse(StringUtil.isEmpty(params.get("dictType")), "dictType缺失");
        PageResult<SettingDictDataVo> list = iSettingDictDataService.list(pageParam, params);
        return AjaxResult.success(list);
    }

    /**
     * 字典数据详情
     *
     * @author fzr
     * @param id 主键
     * @return Object
     */
    @GetMapping("/detail")
    public AjaxResult detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SettingDictDataVo vo = iSettingDictDataService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 字典数据新增
     *
     * @author fzr
     * @param dictDataParam 参数
     * @return AjaxResult
     */
    @PostMapping("/add")
    public AjaxResult add(@Validated(value = DictDataParam.create.class) @RequestBody DictDataParam dictDataParam) {
        iSettingDictDataService.add(dictDataParam);
        return AjaxResult.success();
    }

    /**
     * 字典数据编辑
     *
     * @author fzr
     * @param dictDataParam 参数
     * @return AjaxResult
     */
    @PostMapping("/edit")
    public Object edit(@Validated(value = DictDataParam.update.class) @RequestBody DictDataParam dictDataParam) {
        iSettingDictDataService.edit(dictDataParam);
        return AjaxResult.success();
    }

    /**
     * 字典数据删除
     *
     * @author fzr
     * @param dictDataParam 参数
     * @return AjaxResult
     */
    @PostMapping("/del")
    public AjaxResult del(@Validated(value = DictDataParam.delete.class) @RequestBody DictDataParam dictDataParam) {
        iSettingDictDataService.del(dictDataParam.getIds());
        return AjaxResult.success();
    }

}
