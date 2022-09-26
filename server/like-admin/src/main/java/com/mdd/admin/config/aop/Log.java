package com.mdd.admin.config.aop;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块
     * @return String
     */
    String title() default "";

    /**
     * 模块
     * @return String
     */
    RequestType requestType() default RequestType.Default;

}
