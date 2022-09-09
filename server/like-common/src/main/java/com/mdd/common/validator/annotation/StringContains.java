package com.mdd.common.validator.annotation;

import com.mdd.common.validator.StringContainsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StringContainsValidator.class)
@Target({ ElementType.PARAMETER,ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface StringContains {

    String message() default "字符串不符合规则";

    String[] values() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };

}
