package com.learn.entity;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

import java.util.List;

public class Address {

    private String privince;

    @NotNull(message = "城市不能为空")
    @NotBlank(message = "城市不能为空")
    private String city;

    @NotNull(message = "streets不能为空")
    private List<String> streets;

    public String getPrivince() {
        return privince;
    }

    public void setPrivince(String privince) {
        this.privince = privince;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getStreets() {
        return streets;
    }

    public void setStreets(List<String> streets) {
        this.streets = streets;
    }

    @Override
    public String toString() {
        return "Address{" +
                "privince='" + privince + '\'' +
                ", city='" + city + '\'' +
                ", streets=" + streets +
                '}';
    }
}
