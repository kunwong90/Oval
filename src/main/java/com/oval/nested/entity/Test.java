package com.oval.nested.entity;

import net.sf.oval.ConstraintViolation;

import java.util.List;

/**
 * Created by wangkun on 2017/5/15.
 */
public class Test {

    public static void main(String[] args) {
        CustomOValValidator validator = new CustomOValValidator();
        Person person = new Person();
        Address address = new Address();
        address.setCity("南京");
        person.setAddress(address);

        List<ConstraintViolation> violationsMan = validator.validate(person);
        for (ConstraintViolation violation : violationsMan) {
            System.out.println(violation.getMessage());
        }
    }
}
