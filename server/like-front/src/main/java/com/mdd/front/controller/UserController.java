package com.mdd.front.controller;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.utils.ConfigUtil;
import com.mdd.front.LikeFrontThreadLocal;
import com.mdd.front.service.IUserService;
import com.mdd.front.vo.user.UserCenterVo;
import com.mdd.front.vo.user.UserInfoVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

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
     * 绑定手机号
     *
     * @author fzr
     * @return Object
     */
    @PostMapping("/bindMobile")
    public Object bindMobile() {
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
        Map<String, Object> map = iUserService.mnpMobile(params.get("code").trim());
        return AjaxResult.success(map);
    }

}
