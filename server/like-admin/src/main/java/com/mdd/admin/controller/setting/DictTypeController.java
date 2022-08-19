package com.mdd.admin.controller.setting;

import com.mdd.admin.service.setting.ISettingDictTypeService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.validate.setting.DictTypeParam;
import com.mdd.admin.vo.setting.DictTypeVo;
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
public class DictTypeController {

    @Resource
    ISettingDictTypeService iSettingDictTypeService;

    /**
     * 字典类型所有
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/all")
    public Object all() {
        List<DictTypeVo> list = iSettingDictTypeService.all();
        return AjaxResult.success(list);
    }

    /**
     * 字典类型列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<DictTypeVo> list = iSettingDictTypeService.list(pageParam, params);
        return AjaxResult.success(list);
    }

    /**
     * 字典类型详情
     *
     * @author fzr
     * @param id 主键
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        DictTypeVo vo = iSettingDictTypeService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 字典类型新增
     *
     * @author fzr
     * @param dictTypeParam 参数
     * @return Object
     */
    @PostMapping("/add")
    public Object add(@Validated(value = DictTypeParam.create.class) @RequestBody DictTypeParam dictTypeParam) {
        iSettingDictTypeService.add(dictTypeParam);
        return AjaxResult.success();
    }

    /**
     * 字典类型编辑
     *
     * @author fzr
     * @param dictTypeParam 参数
     * @return Object
     */
    @PostMapping("/edit")
    public Object edit(@Validated(value = DictTypeParam.update.class) @RequestBody DictTypeParam dictTypeParam) {
        iSettingDictTypeService.edit(dictTypeParam);
        return AjaxResult.success();
    }

    /**
     * 字典类型删除
     *
     * @author fzr
     * @param dictTypeParam 参数
     * @return Object
     */
    @PostMapping("/del")
    public Object del(@Validated(value = DictTypeParam.delete.class) @RequestBody DictTypeParam dictTypeParam) {
        iSettingDictTypeService.del(dictTypeParam.getIds());
        return AjaxResult.success();
    }

}
