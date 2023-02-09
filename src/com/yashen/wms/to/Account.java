package com.yashen.wms.to;

public class Account {
    private String firstName;
    private String lastname;
    private String userName;
    private String password;
    private String email;
    private String mobile;
    private String accType;

    public Account(String firstName, String lastname, String userName, String password, String email, String mobile, String accType) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.accType = accType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }
}
