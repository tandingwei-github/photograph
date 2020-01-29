package com.tdw.photograph.bean;

import java.util.Date;

public class User {
    private String username;

    private String userPhone;

    private String password;

    private String userNick;

    private String userIsStopped;

    private Date userLastLogin;

    private Integer userShared;

    private Integer userPurchased;

    private Integer userCommented;

    private String userLogo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick == null ? null : userNick.trim();
    }

    public String getUserIsStopped() {
        return userIsStopped;
    }

    public void setUserIsStopped(String userIsStopped) {
        this.userIsStopped = userIsStopped == null ? null : userIsStopped.trim();
    }

    public Date getUserLastLogin() {
        return userLastLogin;
    }

    public void setUserLastLogin(Date userLastLogin) {
        this.userLastLogin = userLastLogin;
    }

    public Integer getUserShared() {
        return userShared;
    }

    public void setUserShared(Integer userShared) {
        this.userShared = userShared;
    }

    public Integer getUserPurchased() {
        return userPurchased;
    }

    public void setUserPurchased(Integer userPurchased) {
        this.userPurchased = userPurchased;
    }

    public Integer getUserCommented() {
        return userCommented;
    }

    public void setUserCommented(Integer userCommented) {
        this.userCommented = userCommented;
    }

    public String getUserLogo() {
        return userLogo;
    }

    public void setUserLogo(String userLogo) {
        this.userLogo = userLogo == null ? null : userLogo.trim();
    }
}