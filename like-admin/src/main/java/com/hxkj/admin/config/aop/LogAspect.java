package com.hxkj.admin.config.aop;

import com.alibaba.fastjson.JSON;
import com.hxkj.admin.LikeAdminThreadLocal;
import com.hxkj.common.entity.log.LogOperate;
import com.hxkj.common.mapper.log.LogOperateMapper;
import com.hxkj.common.utils.HttpUtil;
import com.hxkj.common.utils.IpUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

@Aspect
@Component
public class LogAspect {

    @Resource
    LogOperateMapper logOperateMapper;

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 声明切面点拦截那些类
     */
    @Pointcut("@annotation(com.hxkj.admin.config.aop.LogAnnotation)")
    private void pointCutMethodController() {}

    /**
     * 环绕通知前后增强
     */
    @Around(value = "pointCutMethodController()")
    public Object doAroundService(ProceedingJoinPoint joinPoint) throws Throwable {
        // 开始时间
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = joinPoint.proceed();
        // 执行结束
        long endTime = System.currentTimeMillis();
        // 执行时长
        long takeTime = endTime - beginTime;
        // 保存日志
        recordLog(joinPoint, beginTime, endTime, takeTime);
        // 返回结果
        return result;
    }

    /**
     * 记录日志信息
     * @param joinPoint joinPoint
     * @param startTime 开始时间(毫秒)
     * @param endTime 结束时间(毫秒)
     * @param takeTime 执行时长(毫秒)
     */
    private void recordLog(ProceedingJoinPoint joinPoint, long startTime, long endTime, long takeTime) {
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {

                // 取得请求对象
                HttpServletRequest request = requestAttributes.getRequest();

                // 获取当前的用户
                Integer adminId = LikeAdminThreadLocal.getAdminId();

                // 获取日志注解
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                Method method = signature.getMethod();
                LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);

                // 方法名称
                String className = joinPoint.getTarget().getClass().getName();
                String methodName = joinPoint.getSignature().getName();

                // 获取请求参数
                String queryString = request.getQueryString();
                Object[] args = joinPoint.getArgs();
                String params = "";
                if(args.length>0){
                    if("POST".equals(request.getMethod())){
                        params = JSON.toJSONString(args);
                    } else if("GET".equals(request.getMethod())){
                        params = queryString;
                    }
                }

                // 数据库日志
                LogOperate model = new LogOperate();
                model.setAdminId(adminId);
                model.setTitle(logAnnotation.title());
                model.setIp(IpUtil.getIpAddress(request));
                model.setType(request.getMethod());
                model.setMethod(className + "." + methodName + "()");
                model.setUrl(HttpUtil.route());
                model.setArgs(params);
                model.setStartTime(startTime / 1000);
                model.setEndTime(endTime / 1000);
                model.setTaskTime(takeTime);
                model.setCreateTime(System.currentTimeMillis() / 1000);
                logOperateMapper.insert(model);
            }
        } catch (Exception e) {
            log.error("异常信息:{}", e.getMessage());
        }
    }

}
