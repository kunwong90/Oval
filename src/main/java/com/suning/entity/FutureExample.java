package com.suning.entity;

import net.sf.oval.constraint.Future;

import java.util.Date;

public class FutureExample {

    /**
     * 格式为yyyy-MM-dd HH:mm:ss
     */
    @Future
    private String date;

    @Future
    private Date date1;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }
}
