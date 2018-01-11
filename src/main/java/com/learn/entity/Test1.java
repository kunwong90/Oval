package com.learn.entity;

import com.learn.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

public class Test1 {

    public static void main(String[] args) {


        People people = new People();
        people.setId("2");
        List<Address> addresses = new ArrayList<>();
        Address address = new Address();
        address.setCity("nanjing");
        address.setPrivince(null);
        List<String> streets = new ArrayList<>();
        streets.add("xuanwujiedao");
        streets.add("xuanwujiedao1");
        address.setStreets(streets);
        addresses.add(address);
        people.setAddresses(addresses);
        ValidationUtils.validate(people);
        System.out.println(people);



    }
}
