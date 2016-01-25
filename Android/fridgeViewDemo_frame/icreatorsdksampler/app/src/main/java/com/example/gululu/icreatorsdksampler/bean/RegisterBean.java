package com.example.gululu.icreatorsdksampler.bean;

import java.io.Serializable;

/**
 *
 * 注册页面 用户注册实体类
 * Created by yx on 2015/10/8.
 */
public class RegisterBean implements Serializable {
    private String userName;
    private String password;
    private String mobile;
    private String authCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
