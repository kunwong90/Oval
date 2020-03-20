package com.learn.utils;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangkun
 * 对象校验
 */
public final class ValidationUtils {

    private static Validator validator = new Validator();

    private ValidationUtils() {

    }

    public static void validate(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("入参不能为空");
        }
        List<ConstraintViolation> result = new ArrayList<>();
        List<ConstraintViolation> constraintViolation = getRootViolation(validator.validate(obj), result);
        if (CollectionUtils.isNotEmpty(constraintViolation)) {
            throw new IllegalArgumentException(constraintViolation.get(0).getMessage());
        }
    }

    private static List<ConstraintViolation> getRootViolation(List<ConstraintViolation> shallowViolationSet, List<ConstraintViolation> result) {
        if (CollectionUtils.isNotEmpty(shallowViolationSet)) {
            for (ConstraintViolation constraintViolation : shallowViolationSet) {
                if (constraintViolation.getCauses() != null) {
                    getRootViolation(Arrays.asList(constraintViolation.getCauses()), result);
                } else {
                    result.add(constraintViolation);
                }
            }
        }
        return result;
    }
}
