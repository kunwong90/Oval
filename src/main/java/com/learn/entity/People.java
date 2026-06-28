package com.learn.entity;

import net.sf.oval.constraint.Assert;
import net.sf.oval.constraint.AssertValid;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

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

    @NotBlank(message = "性别不能为空字符串或空格")
    @Assert(expr = "_value == '男' || _value == '女'", lang = "js", message = "性别只能输入男或女")
    private String sex;

    @Assert(expr = "_this.currentSaleNo != 'Y' + _this.storeCode + '1999'", lang = "js", message = "正式工号不能为空")
    private String realSaleNo;



    @Assert(expr = "_this.phone != null && _this.phone != '' || _this.email != null && _this.email != '' || _this.identity != null && _this.identity != ''", lang = "js", message = "phone、email、identity不能同时为空")
    private String phone;

    private String email;

    private String identity;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRealSaleNo() {
        return realSaleNo;
    }

    public void setRealSaleNo(String realSaleNo) {
        this.realSaleNo = realSaleNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
