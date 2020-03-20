package com.learn.annotation;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;
import org.apache.commons.lang3.StringUtils;

public class NotNullAndBlankCheck extends AbstractAnnotationCheck<NotNullAndBlank> {
    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context, Validator validator) throws OValException {
        if (validatedObject == null) {
            return false;
        }

        if (valueToValidate == null) {
            return false;
        } else {
            if (context.getCompileTimeType().equals(String.class)) {
                return StringUtils.isNotBlank(valueToValidate.toString());
            }
        }

        return false;
    }
}
