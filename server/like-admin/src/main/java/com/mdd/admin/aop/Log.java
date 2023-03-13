package com.mdd.admin.aop;

import com.mdd.admin.aop.aspect.RequestType;

import java.lang.annotation.*;

/**
 * 日志记录类
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块名称
     *
     * @return String
     */
    String title() default "";

    /**
     * 请求类型
     *
     * @return String
     */
    RequestType requestType() default RequestType.Default;

}
