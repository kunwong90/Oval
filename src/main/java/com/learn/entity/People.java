package com.learn.entity;

import net.sf.oval.constraint.*;

import java.util.List;

/**
 * 需求
 * 当id=1时城市不能为空
 * 当id=2时streets不能为空
 */
public class People {

    @NotNull(message = "id不能为空")
    @NotBlank(message = "id不能为空")
    private String id;

    @AssertValid
    @NotNull(message = "地址列表不能为空", target = "jxpath:addresses[0]/city")
    private List<Address> addresses;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "People{" +
                "id='" + id + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
