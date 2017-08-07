package com.suning.entity;

import com.suning.utils.ValidationUtils;
import net.sf.oval.constraint.*;

public class AssertExample {

    @NotBlank(message = "性别不能为空字符串或空格")
    @Assert(expr = "_value == '男' || _value == '女'", lang = "js", message = "性别只能输入男或女")
    private String sex;

    @Assert(expr = "_this.currentSaleNo != 'Y' + _this.storeCode + '1999'", lang = "js", message = "正式工号不能为空")
    private String realSaleNo;

    @NotNull(message = "当前登录人不能为空")
    @NotBlank(message = "当前登录人不能为空")
    private String currentSaleNo;


    @NotBlank(message = "门店号不能为空")
    @NotNull(message = "门店号不能为空")
    private String storeCode;

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
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

    public String getCurrentSaleNo() {
        return currentSaleNo;
    }

    public void setCurrentSaleNo(String currentSaleNo) {
        this.currentSaleNo = currentSaleNo;
    }

    public static void main(String[] args) {
        AssertExample assertExample = new AssertExample();
        assertExample.setSex("男");
        assertExample.setStoreCode("837A");
        assertExample.setCurrentSaleNo("Y837A1999");
        ValidationUtils.validate(assertExample);

    }
}
