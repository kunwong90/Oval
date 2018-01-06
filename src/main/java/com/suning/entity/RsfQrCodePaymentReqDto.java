package com.suning.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import net.sf.oval.constraint.Assert;
import net.sf.oval.constraint.NotNull;


public class RsfQrCodePaymentReqDto implements Serializable {

    /**
     * 序列化Id
     */
    private static final long serialVersionUID = 7938853511797022205L;

    /**
     * 来源系统 <br>
     * 默认传‘CPOS','POS','NPOS'，'APOS','RMPOS'
     */
    @NotNull(message = "系统来源不可为空!")
    private String systemSource;


    /**
     * 付款类型<br>
     * 01'表示扫一扫，'02'表示付款码，没有此节点表扫一扫,05:支付宝扫一扫,06:EPP扫一扫
     */
    @NotNull(when = "js:_this.systemSource == 'PPOS'", message = "苏宁之家支付类型不能为空")
    private String payType;


    
    /**
     * 20171225
     * 流水号 (苏宁之家 支付宝传M单号+01,02...,EPPE只传M单号,没有01,02...)
     */
    @NotNull(when = "js:_this.systemSource == 'PPOS' && _this.payType == '05'", message = "苏宁之家支付宝支付流水号不能为空")
    private String serialNumber;

    private String mergerOrderNo;

    public String getSystemSource() {
        return systemSource;
    }

    public void setSystemSource(String systemSource) {
        this.systemSource = systemSource;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMergerOrderNo() {
        return mergerOrderNo;
    }

    public void setMergerOrderNo(String mergerOrderNo) {
        this.mergerOrderNo = mergerOrderNo;
    }

    @Override
    public String toString() {
        return "RsfQrCodePaymentReqDto{" +
                "systemSource='" + systemSource + '\'' +
                ", payType='" + payType + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", mergerOrderNo='" + mergerOrderNo + '\'' +
                '}';
    }
}
