package com.example.framework.appframework.model;

import java.io.Serializable;

/**
 * 公司：
 * 项目：大
 * 简述：保存用户登录信息Bean
 * 作者：Chenxp
 * 时间：2017/10/18
 * 版本：V1.0.0
 */
public class LoginInfo implements Serializable {

    private static final long serialVersionUID = 3468562762302884480L;

    private String address;
    private String captchaCode;
    private String captchaValue;
    private String emailAccount;
    private String emailCode;
    private String enEddress;
    private String firmEnName;
    private String firmName;
    private String mobile;
    private String oldEmail;
    private String password;
    private String oldPassword;
    private String username;
    private String rememberMe;
    private String firmId;

    private String userId;
    private String userCode;
    private String userName;
    private String deptName;
    private String deptCode;
    private String userType;
    private String jwt;
    private String firmStatus;

    public LoginInfo(){

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getFirmStatus() {
        return firmStatus;
    }

    public void setFirmStatus(String firmStatus) {
        this.firmStatus = firmStatus;
    }

    public String getFirmId() {
        return firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
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

    public String getEnEddress() {
        return enEddress;
    }

    public void setEnEddress(String enEddress) {
        this.enEddress = enEddress;
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
