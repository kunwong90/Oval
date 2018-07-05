package com.learn.nested.entity;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;

/**
 * Created by wangkun on 2017/5/15.
 */
public class Address {

    @NotNull(message = "省份不能为空")
    @Length(min = 3, max = 5, message = "省份长度只能在{min}到{max}之间")
    private String province;

    @NotNull(message = "城市不能为空")
    private String city;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
