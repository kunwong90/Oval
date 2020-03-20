package com.learn.annotation;

import net.sf.oval.ConstraintTarget;
import net.sf.oval.configuration.annotation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，校验入参不能为null和空格
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Constraint(checkWith = NotNullAndBlankCheck.class)
public @interface NotNullAndBlank {


    ConstraintTarget[] appliesTo() default {ConstraintTarget.VALUES};

    String errorCode() default "";

    String message() default "";

}
