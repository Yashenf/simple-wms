package com.yashen.wms.to;

import java.sql.Date;

public class Order {
    private String oid;
    private Date date;
    private String customer;
    private double total;
    private double discount;

    public Order(String oid, Date date, String customer, double total, double discount) {
        this.oid = oid;
        this.date = date;
        this.customer = customer;
        this.total = total;
        this.discount = discount;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
