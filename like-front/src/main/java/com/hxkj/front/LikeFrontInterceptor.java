package com.hxkj.front;

import com.alibaba.fastjson.JSON;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.enums.HttpEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
@Component
public class LikeFrontInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 404拦截
        response.setContentType("application/json;charset=utf-8");
        if (response.getStatus() == 404) {
            AjaxResult result = AjaxResult.failed(HttpEnum.REQUEST_404_ERROR.getCode(), HttpEnum.REQUEST_404_ERROR.getMsg());
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        // 验证通过继续操作
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LikeFrontThreadLocal.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
