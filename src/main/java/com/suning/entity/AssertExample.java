package com.suning.entity;

import net.sf.oval.constraint.*;

public class AssertExample {

    @NotBlank(message = "性别不能为空字符串或空格")
    @Assert(expr = "_value == '男' || _value == '女'", lang = "js", message = "性别只能输入男或女")
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
