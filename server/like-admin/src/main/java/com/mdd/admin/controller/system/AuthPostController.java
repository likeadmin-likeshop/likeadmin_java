package com.mdd.admin.controller.system;

import com.mdd.admin.service.system.ISystemAuthPostService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.validate.system.SystemAuthPostParam;
import com.mdd.admin.vo.system.SystemAuthPostVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 系统岗位管理
 */
@RestController
@RequestMapping("api/system/post")
public class AuthPostController {

    @Resource
    ISystemAuthPostService iSystemAuthPostService;

    /**
     * 岗位所有
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/all")
    public AjaxResult all() {
        List<SystemAuthPostVo> vos = iSystemAuthPostService.all();
        return AjaxResult.success(vos);
    }

    /**
     * 岗位列表
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public AjaxResult list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SystemAuthPostVo> list = iSystemAuthPostService.list(pageParam, params);
        return AjaxResult.success(list);
    }

    /**
     * 岗位详情
     *
     * @author fzr
     * @param id 主键
     * @return AjaxResult
     */
    @GetMapping("/detail")
    public AjaxResult detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SystemAuthPostVo vo = iSystemAuthPostService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 岗位新增
     *
     * @author fzr
     * @param systemAuthPostParam 参数
     * @return AjaxResult
     */
    @PostMapping("/add")
    public AjaxResult add(@Validated(value = SystemAuthPostParam.create.class) @RequestBody SystemAuthPostParam systemAuthPostParam) {
        iSystemAuthPostService.add(systemAuthPostParam);
        return AjaxResult.success();
    }

    /**
     * 岗位编辑
     *
     * @author fzr
     * @param systemAuthPostParam 参数
     * @return AjaxResult
     */
    @PostMapping("/edit")
    public AjaxResult edit(@Validated(value = SystemAuthPostParam.update.class) @RequestBody SystemAuthPostParam systemAuthPostParam) {
        iSystemAuthPostService.edit(systemAuthPostParam);
        return AjaxResult.success();
    }

    /**
     * 岗位删除
     *
     * @author fzr
     * @param systemAuthPostParam 参数
     * @return AjaxResult
     */
    @PostMapping("/del")
    public AjaxResult del(@Validated(value = SystemAuthPostParam.delete.class) @RequestBody SystemAuthPostParam systemAuthPostParam) {
        iSystemAuthPostService.del(systemAuthPostParam.getId());
        return AjaxResult.success();
    }

}
