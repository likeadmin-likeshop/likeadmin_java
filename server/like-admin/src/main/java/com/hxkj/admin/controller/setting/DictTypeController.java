package com.hxkj.admin.controller.setting;

import com.hxkj.admin.service.setting.IDictTypeService;
import com.hxkj.admin.validate.common.PageParam;
import com.hxkj.admin.validate.setting.DictTypeParam;
import com.hxkj.admin.vo.setting.DictTypeVo;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("api/setting/dict/type")
public class DictTypeController {

    @Resource
    IDictTypeService iDictTypeService;

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
        PageResult<DictTypeVo> list = iDictTypeService.list(pageParam, params);
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
        DictTypeVo vo = iDictTypeService.detail(id);
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
        iDictTypeService.add(dictTypeParam);
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
        iDictTypeService.edit(dictTypeParam);
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
        iDictTypeService.del(dictTypeParam.getId());
        return AjaxResult.success();
    }

}