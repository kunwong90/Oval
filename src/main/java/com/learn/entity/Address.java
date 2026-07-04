package com.learn.entity;

import net.sf.oval.constraint.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Address {

    private String province;

    @NotNull(message = "城市不能为空")
    @NotBlank(message = "城市不能为空")
    private String city;

    @NotNull(message = "streets不能为空")
    private List<String> streets;

    @NotNegative(message = "重试次数不能为负数")
    private Integer retryTimes;

    @NotNull(message = "金额不能为空")
    @Min(value = 1, message = "金额必须大于0")
    private Long amount;

    @NotNull(message = "退款模式不能为空")
    @Assert(expr = "_value == 'REFUND' || _value == 'RECOVER'", lang = "js", message = "退款模式只能是REFUND或RECOVER")
    private String refundMode;

    @Assert(expr = "_this.refundMode != 'REFUND' || _value != null", lang = "js", message = "退款模式为REFUND时，退款时间不能为空")
    private String refundTime;


    @NotNull(message = "List不允许为空")
    @Size(min = 1, message = "List元素个数至少大于等于1")
    private List<String> list;

    @NotNull(message = "Set不允许为空")
    @Size(min = 1, message = "Set元素个数至少大于等于1")
    private Set<String> set;

    @NotNull(message = "Map不允许为空")
    @Size(min = 1, message = "Map元素个数至少大于等于1")
    private Map<String, String> map;

    @NotNull(message = "数组不允许为")
    @Size(min = 1, message = "数组元素个数至少大于等于1")
    private String[] array;

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


    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getRefundMode() {
        return refundMode;
    }

    public void setRefundMode(String refundMode) {
        this.refundMode = refundMode;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }


    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }
}
