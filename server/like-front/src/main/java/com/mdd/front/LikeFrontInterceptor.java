package com.mdd.front;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.common.aop.NotLogin;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.entity.user.User;
import com.mdd.common.enums.ErrorEnum;
import com.mdd.common.exception.LoginException;
import com.mdd.common.mapper.user.UserMapper;
import com.mdd.common.util.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 拦截器
 */
@Component
public class LikeFrontInterceptor implements HandlerInterceptor {

    @Resource
    UserMapper userMapper;

    /**
     * 前置处理器
     *
     * @param request 请求
     * @param response 响应
     * @param handler 处理
     * @return boolean
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {

        // 判断请求接口
        response.setContentType("application/json;charset=utf-8");
        String reqUri = request.getRequestURI();
        if (!(handler instanceof HandlerMethod) || !reqUri.startsWith("/api")) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        // 记录当前平台
        String terminal = request.getHeader("terminal");
        LikeFrontThreadLocal.put("terminal", StringUtils.isEmpty(terminal) ? "1" : terminal);

        // 登录权限校验
        try {
            Method method = this.obtainAop(handler);
            this.checkLogin(method);
        } catch (LoginException e) {
            AjaxResult<Object> result = AjaxResult.failed(e.getCode(), e.getMsg());
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        // 验证通过继续操作
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * 后置处理器
     *
     * @param request 请求
     * @param response 响应
     * @param handler 处理
     * @param ex 异常
     * @throws Exception 异常
     */
    @Override
    public void afterCompletion(@NonNull HttpServletRequest request,
                                @NonNull HttpServletResponse response,
                                @NonNull Object handler, Exception ex) throws Exception {
        LikeFrontThreadLocal.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 提取注解
     *
     * @param handler 处理器
     * @return Method
     * @throws Exception 异常
     */
    private Method obtainAop(@NotNull Object handler) throws Exception {
        String[] objArr = handler.toString().split("#");
        String methodStr = objArr[1].split("\\(")[0];
        String classStr = objArr[0];
        Class<?> clazz = Class.forName(classStr);

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodStr)) {
                return method;
            }
        }

        return null;
    }

    /**
     * 登录验证
     *
     * @param method 方法类
     * @author fzr
     */
    private void checkLogin(Method method) {
        for (int i=0; i<=0; i++) {
            // 免登校验
            if (StringUtils.isNotNull(method) && method.isAnnotationPresent(NotLogin.class)) {
                try {
                    Object id = StpUtil.getLoginId();
                    if (StringUtils.isNotNull(id)) {
                        Integer userId = Integer.parseInt(id.toString());
                        User user = userMapper.selectOne(new QueryWrapper<User>()
                                .select("id,sn,username")
                                .eq("id", userId)
                                .eq("is_disable", 0)
                                .eq("is_delete", 0)
                                .last("limit 1"));

                        Integer uid = StringUtils.isNull(user) ? 0 : userId;
                        LikeFrontThreadLocal.put("userId", uid);
                    }
                } catch (Exception ignored) {}
                break;
            }

            // 令牌校验
            String token = StpUtil.getTokenValue();
            if (StringUtils.isNull(token) || StringUtils.isBlank(token)) {
                Integer errCode = ErrorEnum.TOKEN_EMPTY.getCode();
                String errMsg = ErrorEnum.TOKEN_EMPTY.getMsg();
                throw new LoginException(errCode, errMsg);
            }

            // 登录校验
            Object id = StpUtil.getLoginId();
            if (StringUtils.isNull(id)) {
                Integer errCode = ErrorEnum.TOKEN_INVALID.getCode();
                String errMsg = ErrorEnum.TOKEN_INVALID.getMsg();
                throw new LoginException(errCode, errMsg);
            }

            // 用户信息缓存
            Integer userId = Integer.parseInt(id.toString());
            User user = userMapper.selectOne(new QueryWrapper<User>()
                    .select("id,sn,username,nickname,mobile,is_disable")
                    .eq("id", userId)
                    .eq("is_delete", 0)
                    .last("limit 1"));

            // 删除校验
            if (StringUtils.isNull(user)) {
                Integer errCode = ErrorEnum.TOKEN_INVALID.getCode();
                String errMsg = ErrorEnum.TOKEN_INVALID.getMsg();
                throw new LoginException(errCode, errMsg);
            }

            // 禁用校验
            if (user.getIsDisable().equals(1)) {
                Integer errCode = ErrorEnum.LOGIN_DISABLE_ERROR.getCode();
                String errMsg = ErrorEnum.LOGIN_DISABLE_ERROR.getMsg();
                throw new LoginException(errCode, errMsg);
            }

            // 写入线程
            LikeFrontThreadLocal.put("userId", user.getId());
            LikeFrontThreadLocal.put("userSn", user.getSn());
            LikeFrontThreadLocal.put("username", user.getUsername());
            LikeFrontThreadLocal.put("nickname", user.getNickname());
            LikeFrontThreadLocal.put("mobile", user.getMobile());
        }
    }

}
