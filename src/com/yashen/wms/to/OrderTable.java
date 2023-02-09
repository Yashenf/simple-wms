package com.yashen.wms.to;

import java.sql.Date;

public class OrderTable {
    private int no;
    private String oid;
    private Date date;
    private String customer;
    private double total;
    private double discount;

    public OrderTable(int no, String oid, Date date, String customer, double total, double discount) {
        this.no = no;
        this.oid = oid;
        this.date = date;
        this.customer = customer;
        this.total = total;
        this.discount = discount;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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
