package com.mdd.common.validator;

import com.mdd.common.validator.annotation.IntegerContains;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * 验证数字是否在数组中
 */
public class IntegerContainsValidator implements ConstraintValidator<IntegerContains, Integer> {

    private Set<Integer> limitValues;

    @Override
    public void initialize (IntegerContains integerContains) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : integerContains.values()) {
            set.add(i);
        }
        limitValues = set;
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return limitValues.contains(value);
    }

}
