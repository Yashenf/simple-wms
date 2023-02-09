package com.yashen.wms.to;

public class Customer {
    private String cId;
    private String name;
    private String tp;
    private String email;
    private Address address;

    public Customer() {
    }

    public Customer(String cId, String name, String tp, String email, Address address) {
        this.cId = cId;
        this.name = name;
        this.tp = tp;
        this.email = email;
        this.address = address;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
