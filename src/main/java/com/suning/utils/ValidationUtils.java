package com.suning.utils;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ValidationUtils {

    private static Validator validator = new Validator();

    private ValidationUtils() {

    }

    public static void validate(Object obj) {
        List<ConstraintViolation> result = new ArrayList<>();
        List<ConstraintViolation> constraintViolation = getRootViolation(validator.validate(obj), result);
        if (CollectionUtils.isNotEmpty(constraintViolation)) {
            throw new RuntimeException(constraintViolation.get(0).getMessage());
        }
    }

    private static List<ConstraintViolation> getRootViolation(List<ConstraintViolation> shallowViolationSet, List<ConstraintViolation> result) {
        final List<ConstraintViolation> rootViolationSet = new ArrayList<ConstraintViolation>();
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
