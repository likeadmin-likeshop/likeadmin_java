package com.mdd.front.controller;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.exception.OperateException;
import com.mdd.front.LikeFrontThreadLocal;
import com.mdd.front.service.IUserService;
import com.mdd.front.vo.user.UserCenterVo;
import com.mdd.front.vo.user.UserInfoVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 用户管理表
 */
@RestController
@RequestMapping("api/user")
public class UserController {

    @Resource
    IUserService iUserService;

    /**
     * 个人中心
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/center")
    public Object center() {
        UserCenterVo vo = iUserService.center(LikeFrontThreadLocal.getUserId());
        return AjaxResult.success(vo);
    }

    /**
     * 个人信息
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/info")
    public Object info() {
        UserInfoVo vo = iUserService.info(LikeFrontThreadLocal.getUserId());
        return AjaxResult.success(vo);
    }

    /**
     * 编辑信息
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @PostMapping("/edit")
    public Object edit(@RequestBody Map<String, String> params) {
        Assert.notNull(params.get("field"), "field参数缺失");
        Assert.notNull(params.get("value"), "value参数缺失");
        Integer userId = LikeFrontThreadLocal.getUserId();
        iUserService.edit(params, userId);
        return AjaxResult.success();
    }

    /**
     * 修改密码
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @PostMapping("/changePwd")
    public Object changePwd(@RequestBody Map<String, String> params) {
        Assert.notNull(params.get("password"), "password参数缺失");
        if(!Pattern.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$", params.get("password"))){
            throw new OperateException("密码必须是6-20字母+数字组合!");
        }
        Integer userId = LikeFrontThreadLocal.getUserId();
        iUserService.changePwd(params.get("password"), params.getOrDefault("oldPassword", null), userId);
        return AjaxResult.success();
    }

    /**
     * 绑定手机号
     *
     * @author fzr
     * @return Object
     */
    @PostMapping("/bindMobile")
    public Object bindMobile(@RequestBody Map<String, String> params) {
        Assert.notNull(params.get("type"), "type参数缺失");
        Assert.notNull(params.get("mobile"), "mobile参数缺失");
        Assert.notNull(params.get("code"), "code参数缺失");
        boolean type = Arrays.asList("bind", "change").contains(params.get("type"));
        Assert.isTrue(type, "type类型只能是[bind/change]");
        if(!Pattern.matches("^[1][3,4,5,6,7,8,9][0-9]{9}$", params.get("mobile"))){
            throw new OperateException("手机号格式不正确!");
        }

        Integer userId = LikeFrontThreadLocal.getUserId();
        iUserService.bindMobile(params, userId);
        return AjaxResult.success();
    }

    /**
     * 微信手机号
     *
     * @author fzr
     * @return Object
     */
    @PostMapping("/mnpMobile")
    public Object mnpMobile(@RequestBody Map<String, String> params) {
        Assert.notNull(params.get("code"), "code参数缺失");
        iUserService.mnpMobile(params.get("code").trim());
        return AjaxResult.success();
    }

}
