package com.mdd.admin.controller.system;

import com.mdd.admin.service.ISystemAuthPostService;
import com.mdd.admin.validate.commons.IdValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.system.SystemPostCreateValidate;
import com.mdd.admin.validate.system.SystemPostSearchValidate;
import com.mdd.admin.validate.system.SystemPostUpdateValidate;
import com.mdd.admin.vo.system.SystemAuthPostVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统岗位管理
 */
@RestController
@RequestMapping("api/system/post")
public class SystemAuthPostController {

    @Resource
    ISystemAuthPostService iSystemAuthPostService;

    /**
     * 岗位所有
     *
     * @author fzr
     * @return AjaxResult<List<SystemAuthPostVo>>
     */
    @GetMapping("/all")
    public AjaxResult<List<SystemAuthPostVo>> all() {
        List<SystemAuthPostVo> list = iSystemAuthPostService.all();
        return AjaxResult.success(list);
    }

    /**
     * 岗位列表
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<SystemAuthPostVo>
     */
    @GetMapping("/list")
    public AjaxResult<PageResult<SystemAuthPostVo>> list(@Validated PageValidate pageValidate,
                                                         @Validated SystemPostSearchValidate searchValidate) {
        PageResult<SystemAuthPostVo> list = iSystemAuthPostService.list(pageValidate, searchValidate);
        return AjaxResult.success(list);
    }

    /**
     * 岗位详情
     *
     * @author fzr
     * @param id 主键
     * @return AjaxResult<SystemAuthPostVo>
     */
    @GetMapping("/detail")
    public AjaxResult<SystemAuthPostVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SystemAuthPostVo vo = iSystemAuthPostService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 岗位新增
     *
     * @author fzr
     * @param createValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/add")
    public AjaxResult<Object> add(@Validated @RequestBody SystemPostCreateValidate createValidate) {
        iSystemAuthPostService.add(createValidate);
        return AjaxResult.success();
    }

    /**
     * 岗位编辑
     *
     * @author fzr
     * @param updateValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/edit")
    public AjaxResult<Object> edit(@Validated @RequestBody SystemPostUpdateValidate updateValidate) {
        iSystemAuthPostService.edit(updateValidate);
        return AjaxResult.success();
    }

    /**
     * 岗位删除
     *
     * @author fzr
     * @param idValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/del")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iSystemAuthPostService.del(idValidate.getId());
        return AjaxResult.success();
    }

}
