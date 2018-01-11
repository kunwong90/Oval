package com.learn.entity;

import net.sf.oval.constraint.NotNull;

public class WhenExample {

    private String fieldA;

    @NotNull(when = "js:_this.fieldA != null", message = "fieldA不为null时fieldB也不允许为null")
    private String fieldB;

    public String getFieldA() {
        return fieldA;
    }

    public void setFieldA(String fieldA) {
        this.fieldA = fieldA;
    }

    public String getFieldB() {
        return fieldB;
    }

    public void setFieldB(String fieldB) {
        this.fieldB = fieldB;
    }
}
