package com.mdd.common.aop;

import java.lang.annotation.*;

/**
 * 免登录校验注解类
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotLogin {
}
