package com.oval.nested.entity;

import net.sf.oval.constraint.NotNull;

/**
 * Created by wangkun on 2017/5/15.
 */
public class Person {

    @ValidateNestedProperty
    @NotNull(message = "住址不能为空")
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
