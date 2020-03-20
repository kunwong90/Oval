package com.learn.nested.entity;

import com.learn.annotation.NotNullAndBlank;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

public class Passenger {

    /**
     * 乘客姓名,必填
     */
    @NotNull
    @NotBlank
    private String passengerName;
    /**
     * 证件类型,必填
     * <p>
     * 1				二代身份证或港澳台居民居住证
     * 2				一代身份证
     * C				港澳通行证
     * G				台湾通行证
     * B				护照
     * H				外国人永久居留身份证
     * </p>
     */
    @NotNullAndBlank(message = "证件类型不能为null或空")
    private String passportTypeId;
    /**
     * 证件号,必填
     */
    private String passportNo;

    /**
     * 证件类型名称,必填
     */
    private String passportTypeName;

    /**
     * 旅客类型 1 成人 2儿童 3学生 4残军,必填
     */
    private Integer personType;
    /**
     * 乘客性别0：女，1：男，2：未知,非必填
     */
    private Integer passengerSex;
    /**
     * 出生日期yyyy-MM-dd,非必填
     */
    private String birthdate;

    /**
     * 证件有效截止日期yyyy-MM-dd,非必填
     */
    private String expireDate;
    /**
     * 国家编码,非必填
     */
    private String countryCode;
    /**
     * 乘客手机号,证件类型为身份证时必填，其他证件类型时手机和邮箱二选一
     */
    @NotNull(when = "js:_this.passportTypeId == '1' || _this.passportTypeId == '2'", message = "证件类型为身份证时手机号不能为空")
    private String tel;

    /**
     * 乘客电子邮箱
     */
    private String email;

    /**
     * 核验结果
     * -3请报验 -2冒用 -1未通过 0 待核验 1 已通过  2手机号/邮箱待核验
     */
    private Integer verificationStatus;

    /**
     * 核验结果说明
     */
    private String verificationStatusName;

    /**
     * 乘客手机短信验证码
     */
    private String verificationCode;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassportTypeId() {
        return passportTypeId;
    }

    public void setPassportTypeId(String passportTypeId) {
        this.passportTypeId = passportTypeId;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getPassportTypeName() {
        return passportTypeName;
    }

    public void setPassportTypeName(String passportTypeName) {
        this.passportTypeName = passportTypeName;
    }

    public Integer getPersonType() {
        return personType;
    }

    public void setPersonType(Integer personType) {
        this.personType = personType;
    }

    public Integer getPassengerSex() {
        return passengerSex;
    }

    public void setPassengerSex(Integer passengerSex) {
        this.passengerSex = passengerSex;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(Integer verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getVerificationStatusName() {
        return verificationStatusName;
    }

    public void setVerificationStatusName(String verificationStatusName) {
        this.verificationStatusName = verificationStatusName;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Passenger{");
        sb.append("passengerName='").append(passengerName).append('\'');
        sb.append(", passportTypeId='").append(passportTypeId).append('\'');
        sb.append(", passportNo='").append(passportNo).append('\'');
        sb.append(", passportTypeName='").append(passportTypeName).append('\'');
        sb.append(", personType=").append(personType);
        sb.append(", passengerSex=").append(passengerSex);
        sb.append(", birthdate='").append(birthdate).append('\'');
        sb.append(", expireDate='").append(expireDate).append('\'');
        sb.append(", countryCode='").append(countryCode).append('\'');
        sb.append(", tel='").append(tel).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", verificationStatus=").append(verificationStatus);
        sb.append(", verificationStatusName='").append(verificationStatusName).append('\'');
        sb.append(", verificationCode='").append(verificationCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

