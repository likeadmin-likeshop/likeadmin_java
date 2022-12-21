package com.mdd.admin;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSON;
import com.mdd.admin.config.AdminConfig;
import com.mdd.admin.service.ISystemAuthAdminService;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.enums.HttpEnum;
import com.mdd.common.util.RedisUtils;
import com.mdd.common.util.StringUtils;
import com.mdd.common.util.ToolsUtils;
import com.mdd.common.util.YmlUtils;
import org.jetbrains.annotations.NotNull;
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

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler) throws Exception {

        // 请求的类型
        response.setContentType("application/json;charset=utf-8");
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

        // 演示环境拦截
        if (YmlUtils.get("like.production").equals("true")) {
            List<String> ignoreUrl = Arrays.asList("system:login", "system:logout");
            if (request.getMethod().equals("POST") && !ignoreUrl.contains(auths)) {
                String message = "演示环境不支持修改数据，请下载源码本地部署体验";
                AjaxResult<Object> result = AjaxResult.failed(HttpEnum.NO_PERMISSION.getCode(), message);
                response.getWriter().print(JSON.toJSONString(result));
                return false;
            }
        }

        // Token是否为空
        String token = StpUtil.getTokenValue();
        if (com.baomidou.mybatisplus.core.toolkit.StringUtils.isBlank(token)) {
            AjaxResult<Object> result = AjaxResult.failed(HttpEnum.TOKEN_EMPTY.getCode(), HttpEnum.TOKEN_EMPTY.getMsg());
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        // Token是否过期
        Object id = StpUtil.getLoginId();
        if (StringUtils.isNull(id)) {
            AjaxResult<Object> result = AjaxResult.failed(HttpEnum.TOKEN_INVALID.getCode(), HttpEnum.TOKEN_INVALID.getMsg());
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        // Users是否存在
        if (!RedisUtils.hExists(AdminConfig.backstageManageKey, id)) {
            iSystemAuthAdminService.cacheAdminUserByUid(Integer.parseInt(id.toString()));
        }

        // 获取用户的信息
        String UserStr =  RedisUtils.hGet(AdminConfig.backstageManageKey, String.valueOf(id)).toString();
        Map<String, String> userMap = ToolsUtils.jsonToMap(UserStr);

        // 校验用户被删除
        if (userMap.get("isDelete").equals("1")) {
            AjaxResult<Object> result = AjaxResult.failed(HttpEnum.TOKEN_INVALID.getCode(), HttpEnum.TOKEN_INVALID.getMsg());
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        // 校验用户被禁用
        if (userMap.get("isDisable").equals("1")) {
            AjaxResult<Object> result = AjaxResult.failed(HttpEnum.LOGIN_DISABLE_ERROR.getCode(), HttpEnum.LOGIN_DISABLE_ERROR.getMsg());
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        // 用户写本地线里
        LikeAdminThreadLocal.put("adminId", id);
        LikeAdminThreadLocal.put("roleIds", userMap.get("roleIds"));
        LikeAdminThreadLocal.put("username", userMap.get("username"));
        LikeAdminThreadLocal.put("nickname", userMap.get("nickname"));

        // 免校验权限接口
        List<String> notAuthUri = Arrays.asList(AdminConfig.notAuthUri);
        if (notAuthUri.contains(auths) || Integer.parseInt(id.toString()) == 1) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        // 校验用户的权限
        StpUtil.checkPermission(auths);

        // 验证通过继续操作
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request,
                                @NotNull HttpServletResponse response,
                                @NotNull Object handler, Exception ex) throws Exception {
        LikeAdminThreadLocal.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
