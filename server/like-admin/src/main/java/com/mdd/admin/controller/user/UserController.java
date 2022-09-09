package com.mdd.admin.controller.user;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.admin.service.user.IUserService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.vo.user.UserVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 用户管理
 */
@RestController
@RequestMapping("api/user")
public class UserController {

    @Resource
    IUserService iUserService;

    /**
     * 用户列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<UserVo> list = iUserService.list(pageParam, params);
        return AjaxResult.success(list);
    }

    /**
     * 用户详情
     *
     * @author fzr
     * @param id 主键
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        UserVo vo = iUserService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 用户编辑
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @PostMapping("/edit")
    public Object edit(@RequestBody Map<String, String> params) {
        Assert.notNull(params.get("id"), "id参数缺失");
        Assert.notNull(params.get("field"), "field参数缺失");
        Assert.notNull(params.get("value"), "value参数缺失");
        iUserService.edit(params);
        return AjaxResult.success();
    }

}
