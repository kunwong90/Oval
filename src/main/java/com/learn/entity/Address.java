package com.learn.entity;

import net.sf.oval.constraint.Assert;
import net.sf.oval.constraint.Min;
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
}
