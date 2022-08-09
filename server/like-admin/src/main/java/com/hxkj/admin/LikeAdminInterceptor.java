package com.hxkj.admin;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hxkj.admin.config.AdminConfig;
import com.hxkj.admin.service.system.ISystemAuthAdminService;
import com.hxkj.admin.service.system.ISystemAuthPermService;
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
    ISystemAuthAdminService iSystemAuthAdminService;

    @Resource
    ISystemAuthPermService iSystemAuthPermService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 404拦截
        response.setContentType("application/json;charset=utf-8");
        if (response.getStatus() == 404) {
            AjaxResult result = AjaxResult.failed(HttpEnum.REQUEST_404_ERROR.getCode(), HttpEnum.REQUEST_404_ERROR.getMsg());
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        // 判断请求接口
        if (!(handler instanceof HandlerMethod)) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        // 路由转权限
        String prefix = "/api/";
        String route = request.getRequestURI().replaceFirst(prefix, "");
        String auths = route.replace("/", ":");

        // 免登录接口
        List<String> notLoginUri = Arrays.asList(AdminConfig.notLoginUri);
        if (notLoginUri.contains(auths)) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        // Token是否为空
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            AjaxResult result = AjaxResult.failed(HttpEnum.TOKEN_EMPTY.getCode(), HttpEnum.TOKEN_EMPTY.getMsg());
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        // Token是否过期
        token = AdminConfig.backstageTokenKey + token;
        if (!RedisUtil.exists(token)) {
            AjaxResult result = AjaxResult.failed(HttpEnum.TOKEN_INVALID.getCode(), HttpEnum.TOKEN_INVALID.getMsg());
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        // 用户信息缓存
        String uid = RedisUtil.get(token).toString();
        if (!RedisUtil.hExists(AdminConfig.backstageManageKey, uid)) {
            iSystemAuthAdminService.cacheAdminUserByUid(Integer.parseInt(uid));
        }

        // 校验用户被删除
        Map<String, String> map = ToolsUtil.jsonToMap(RedisUtil.hGet(AdminConfig.backstageManageKey, uid).toString());
        if (map == null || map.get("isDelete").equals("1")) {
            RedisUtil.del(token);
            RedisUtil.hDel(AdminConfig.backstageManageKey, uid);
            AjaxResult result = AjaxResult.failed(HttpEnum.TOKEN_INVALID.getCode(), HttpEnum.TOKEN_INVALID.getMsg());
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        // 校验用户被禁用
        if (map.get("isDisable").equals("1")) {
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
        LikeAdminThreadLocal.put("roleId", map.get("role"));
        LikeAdminThreadLocal.put("username", map.get("username"));
        LikeAdminThreadLocal.put("nickname", map.get("nickname"));

        // 免权限验证接口
        List<String> notAuthUri = Arrays.asList(AdminConfig.notAuthUri);
        if (notAuthUri.contains(auths) || Integer.parseInt(uid) == 1) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        // 校验角色权限是否存在
        String roleId = map.get("role");
        if (!RedisUtil.hExists(AdminConfig.backstageRolesKey, roleId)) {
            iSystemAuthPermService.cacheRoleMenusByRoleId(Integer.parseInt(roleId));
        }

        // 验证是否有权限操作
        String menus = RedisUtil.hGet(AdminConfig.backstageRolesKey, roleId).toString();
        if (menus.equals("") || !Arrays.asList(menus.split(",")).contains(auths)) {
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
