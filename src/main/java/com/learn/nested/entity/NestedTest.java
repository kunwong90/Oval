package com.learn.nested.entity;


import com.learn.utils.ValidationUtils;

public class NestedTest {

    public static void main(String[] args) {
        Person person = new Person();
        Address address = new Address();
        address.setProvince("江苏省");
        person.setAddress(address);

        ValidationUtils.validate(person);
    }
}
