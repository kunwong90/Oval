package com.oval.nested.entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class CustomOValValidator {
    private net.sf.oval.Validator validator;

    public CustomOValValidator() {
        validator = new net.sf.oval.Validator();
    }

    /**
     * Process the validation
     *
     * @param objectToValidate
     */
    public List validate(Object objectToValidate) {
        return doValidate(objectToValidate, new ArrayList());
    }

    private List doValidate(Object target, List errors) {

        List violations = validator.validate(target);

        if (violations != null)
            errors.addAll(violations);

        Field[] fields = getFields(target);

        for (Field field : fields) {
            ValidateNestedProperty validate = field.getAnnotation(ValidateNestedProperty.class);

            if (validate != null) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }

                Object nestedProperty;

                try {
                    nestedProperty = field.get(target);
                } catch (Exception ex) {
                    throw new RuntimeException("Reflexion error", ex);
                }

                if (nestedProperty != null)
                    doValidate(nestedProperty, errors);
            }
        }
        return errors;
    }


    /**
     * Return the list of fields from an object
     *
     * @param target
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Field[] getFields(Object target) {
        Class clazz = target.getClass();
        return clazz.getDeclaredFields();
    }
}
