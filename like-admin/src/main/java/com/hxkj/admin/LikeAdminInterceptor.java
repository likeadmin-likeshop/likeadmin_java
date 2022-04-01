package com.hxkj.admin;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hxkj.admin.config.SystemConfig;
import com.hxkj.admin.service.ISystemAdminService;
import com.hxkj.admin.service.ISystemRoleMenuService;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.enums.HttpEnum;
import com.hxkj.common.utils.RedisUtil;
import com.hxkj.common.utils.ToolsUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 拦截器
 */
@Component
public class LikeAdminInterceptor implements HandlerInterceptor {

    @Resource
    ISystemAdminService iSystemAdminService;

    @Resource
    ISystemRoleMenuService iSystemRoleMenuService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断请求接口
        if (!(handler instanceof HandlerMethod)) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        // 免登录接口
        List<String> notLoginUri = Arrays.asList(SystemConfig.notLoginUri);
        if (notLoginUri.contains(request.getRequestURI())) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        // Token是否为空
        response.setContentType("application/json;charset=utf-8");
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            AjaxResult result = AjaxResult.failed(HttpEnum.TOKEN_EMPTY.getCode(), HttpEnum.TOKEN_EMPTY.getMsg());
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        // Token是否过期
        token = SystemConfig.backstageTokenKey + token;
        if (!RedisUtil.exists(token)) {
            AjaxResult result = AjaxResult.failed(HttpEnum.TOKEN_INVALID.getCode(), HttpEnum.TOKEN_INVALID.getMsg());
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        // 用户信息缓存
        String uid = RedisUtil.get(token).toString();
        if (!RedisUtil.hExists(SystemConfig.backstageManageKey, uid)) {
            iSystemAdminService.cacheAdminUserByUid(Integer.parseInt(uid));
        }

        // 校验用户被删除
        Map<String, Object> map = ToolsUtil.jsonToMap(RedisUtil.hGet(SystemConfig.backstageManageKey, uid).toString());
        if (map == null || map.get("isDelete").toString().equals("1")) {
            RedisUtil.del(token);
            RedisUtil.hDel(SystemConfig.backstageManageKey, uid);
            AjaxResult result = AjaxResult.failed(HttpEnum.TOKEN_INVALID.getCode(), HttpEnum.TOKEN_INVALID.getMsg());
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        // 校验用户被禁用
        if (map.get("isDisable").toString().equals("1")) {
            AjaxResult result = AjaxResult.failed(HttpEnum.LOGIN_DISABLE_ERROR.getCode(), HttpEnum.LOGIN_DISABLE_ERROR.getMsg());
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        // 令牌剩余30分钟自动续签
        if (RedisUtil.ttl(token) < 1800) {
            RedisUtil.expire(token, 7200L);
        }

        // 写入本地线程
        LikeAdminThreadLocal.put("adminId", uid);
        LikeAdminThreadLocal.put("roleId", map.get("role").toString());
        LikeAdminThreadLocal.put("username", map.get("username").toString());
        LikeAdminThreadLocal.put("nickname", map.get("nickname").toString());

        // 免权限验证接口
        List<String> notAuthUri = Arrays.asList(SystemConfig.notLoginUri);
        if (notAuthUri.contains(request.getRequestURI()) || Integer.parseInt(uid) == 1) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        // 校验角色权限是否存在
        String roleId = map.get("role").toString();
        if (!RedisUtil.hExists(SystemConfig.backstageRolesKey, roleId)) {
            iSystemRoleMenuService.cacheRoleMenusByRoleId(Integer.parseInt(roleId));
        }

        // 验证是否有权限操作
        String menus = RedisUtil.hGet(SystemConfig.backstageRolesKey, roleId).toString();
        if (menus.equals("") || !Arrays.asList(menus.split(",")).contains(request.getRequestURI())) {
            AjaxResult result = AjaxResult.failed(HttpEnum.NO_PERMISSION.getCode(), HttpEnum.NO_PERMISSION.getMsg());
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        // 验证通过继续操作
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LikeAdminThreadLocal.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
