package com.mdd.common.validator.annotation;

import com.mdd.common.validator.IDMustValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IDMustValidator.class)
@Target({ ElementType.PARAMETER,ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IDMust {

    String message() default "id参数必须存在且大于0";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };

}
