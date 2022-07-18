package com.hxkj.admin.controller.setting;

import com.hxkj.admin.service.setting.IDictDataService;
import com.hxkj.admin.validate.common.PageParam;
import com.hxkj.admin.validate.setting.DictDataParam;
import com.hxkj.admin.vo.setting.DictDataVo;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("api/setting/dict/data")
public class DictDataController {

    @Resource
    IDictDataService iDictDataService;

    /**
     * 字典数据列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<DictDataVo> list = iDictDataService.list(pageParam, params);
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
    public Object detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        DictDataVo vo = iDictDataService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 字典数据新增
     *
     * @author fzr
     * @param dictDataParam 参数
     * @return Object
     */
    @PostMapping("/add")
    public Object add(@Validated(value = DictDataParam.create.class) @RequestBody DictDataParam dictDataParam) {
        iDictDataService.add(dictDataParam);
        return AjaxResult.success();
    }

    /**
     * 字典数据编辑
     *
     * @author fzr
     * @param dictDataParam 参数
     * @return Object
     */
    @PostMapping("/edit")
    public Object edit(@Validated(value = DictDataParam.update.class) @RequestBody DictDataParam dictDataParam) {
        iDictDataService.edit(dictDataParam);
        return AjaxResult.success();
    }

    /**
     * 字典数据删除
     *
     * @author fzr
     * @param dictDataParam 参数
     * @return Object
     */
    @PostMapping("/del")
    public Object del(@Validated(value = DictDataParam.delete.class) @RequestBody DictDataParam dictDataParam) {
        iDictDataService.del(dictDataParam.getId());
        return AjaxResult.success();
    }

}
