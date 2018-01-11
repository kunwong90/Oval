package com.learn.entity;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Range;
import net.sf.oval.constraint.ValidateWithMethod;

public class ValidateWithMethod1 {

    @Min(value = 1960, message = "非法的年份")
    private int year = 1977;

    @Range(min = 1, max = 12, message = "非法的月份")
    private int month = 2;

    /**
     * You can write a method within the class that has a single parameter to receive the value to validate
     * and that returns true if the constraint is satisfied and false if it is violated.
     */
    @ValidateWithMethod(methodName = "isValidDay", parameterType = int.class, message = "非法的天数")
    private int day = 31;

    /**
     * 判断工号是否是临时工号，以Y开头的为临时工号
     */
    @NotNull(message = "工号不能为空")
    @ValidateWithMethod(methodName = "isTemporary", parameterType = String.class, message= "非临时工号")
    private String saleNo;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getSaleNo() {
        return saleNo;
    }

    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo;
    }

    private boolean isValidDay(int day) {
        //闰年
        if((year % 4 == 0 && year % 100 != 0 || year % 400 == 0) && month == 2 && day == 29) {
            return true;
        }
        return false;
    }

    private boolean isTemporary(String saleNo) {
        return saleNo.startsWith("Y");
    }
}
