package com.fanzhou.entity;

import java.util.Date;

/**
 * @author fanzhou
 * @create 2020/8/22 0022
 */
public class User {
    private String id;
    private String username;
    private String realName;
    private String password;
    private String sex;
    private String status;
    private Date registerTime;

    public User() {
    }

    public User(String id, String username, String realName, String password, String sex, String status, Date registerTime) {
        this.id = id;
        this.username = username;
        this.realName = realName;
        this.password = password;
        this.sex = sex;
        this.status = status;
        this.registerTime = registerTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", status='" + status + '\'' +
                ", registerTime=" + registerTime +
                '}';
    }

    public Boolean checkUser()  {
        if(this.username == null || this.realName == null || this.sex == null|| this.password == null){
            return false;
        }
        return true;

    }
}
