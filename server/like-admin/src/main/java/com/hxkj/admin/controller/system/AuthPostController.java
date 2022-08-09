package com.hxkj.admin.controller.system;

import com.hxkj.admin.service.system.ISystemAuthPostService;
import com.hxkj.admin.validate.common.PageParam;
import com.hxkj.admin.validate.system.SystemAuthPostParam;
import com.hxkj.admin.vo.system.SystemPostVo;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.validator.annotation.IDMust;
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
     * @return Object
     */
    @GetMapping("/all")
    public Object all() {
        List<SystemPostVo> vos = iSystemAuthPostService.all();
        return AjaxResult.success(vos);
    }

    /**
     * 岗位列表
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SystemPostVo> list = iSystemAuthPostService.list(pageParam, params);
        return AjaxResult.success(list);
    }

    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SystemPostVo vo = iSystemAuthPostService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 岗位新增
     *
     * @author fzr
     * @param systemAuthPostParam 参数
     * @return Object
     */
    @PostMapping("/add")
    public Object add(@Validated(value = SystemAuthPostParam.create.class) @RequestBody SystemAuthPostParam systemAuthPostParam) {
        iSystemAuthPostService.add(systemAuthPostParam);
        return AjaxResult.success();
    }

    /**
     * 岗位编辑
     *
     * @author fzr
     * @param systemAuthPostParam 参数
     * @return Object
     */
    @PostMapping("/edit")
    public Object edit(@Validated(value = SystemAuthPostParam.update.class) @RequestBody SystemAuthPostParam systemAuthPostParam) {
        iSystemAuthPostService.edit(systemAuthPostParam);
        return AjaxResult.success();
    }

    /**
     * 岗位删除
     *
     * @author fzr
     * @param systemAuthPostParam 参数
     * @return Object
     */
    @PostMapping("/del")
    public Object del(@Validated(value = SystemAuthPostParam.delete.class) @RequestBody SystemAuthPostParam systemAuthPostParam) {
        iSystemAuthPostService.del(systemAuthPostParam.getId());
        return AjaxResult.success();
    }

}
