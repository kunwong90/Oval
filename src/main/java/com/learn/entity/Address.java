package com.learn.entity;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNegative;
import net.sf.oval.constraint.NotNull;

import java.util.List;

public class Address {

    private String privince;

    @NotNull(message = "城市不能为空")
    @NotBlank(message = "城市不能为空")
    private String city;

    @NotNull(message = "streets不能为空")
    private List<String> streets;

    //@NotNull
    @NotNegative(message = "重试次数不能为负数")
    private Integer retryTimes;

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

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    @Override
    public String toString() {
        return "Address{" +
                "privince='" + privince + '\'' +
                ", city='" + city + '\'' +
                ", streets=" + streets +
                ", retryTimes=" + retryTimes +
                '}';
    }
}
