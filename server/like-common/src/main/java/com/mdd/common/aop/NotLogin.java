package com.mdd.common.aop;

import java.lang.annotation.*;

/**
 * 免登录校验注解类
 * PS: 改注解无需实现类,由拦截器监听
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotLogin {
}
