package com.example.framework.appframework.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 公司：
 * 项目：
 * 简述：用户信息Bean
 * 作者：Chenxp
 * 时间：2017/9/25
 * 版本：V1.0.0
 */
public class UserInfo implements Parcelable {

    private String address;
    private String captchaCode;
    private String captchaValue;
    private String emailAccount;
    private String emailCode;
    private String enAddress;
    private String firmEnName;
    private String firmName;
    private String mobile;
    private String oldEmail;
    private String password;
    private String oldPassword;
    private String username;
    private String rememberMe;

    public UserInfo(){

    }

    protected UserInfo(Parcel in) {
        address = in.readString();
        captchaCode = in.readString();
        captchaValue = in.readString();
        emailAccount = in.readString();
        emailCode = in.readString();
        enAddress = in.readString();
        firmEnName = in.readString();
        firmName = in.readString();
        mobile = in.readString();
        oldEmail = in.readString();
        password = in.readString();
        username = in.readString();
        rememberMe = in.readString();
        oldPassword = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(address);
        parcel.writeString(captchaCode);
        parcel.writeString(captchaValue);
        parcel.writeString(emailAccount);
        parcel.writeString(emailCode);
        parcel.writeString(enAddress);
        parcel.writeString(firmEnName);
        parcel.writeString(firmName);
        parcel.writeString(mobile);
        parcel.writeString(oldEmail);
        parcel.writeString(password);
        parcel.writeString(username);
        parcel.writeString(rememberMe);
        parcel.writeString(oldPassword);
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getCaptchaValue() {
        return captchaValue;
    }

    public void setCaptchaValue(String captchaValue) {
        this.captchaValue = captchaValue;
    }

    public String getEmailAccount() {
        return emailAccount;
    }

    public void setEmailAccount(String emailAccount) {
        this.emailAccount = emailAccount;
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
    }

    public String getEnAddress() {
        return enAddress;
    }

    public void setEnAddress(String enAddress) {
        this.enAddress = enAddress;
    }

    public String getFirmEnName() {
        return firmEnName;
    }

    public void setFirmEnName(String firmEnName) {
        this.firmEnName = firmEnName;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOldEmail() {
        return oldEmail;
    }

    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
