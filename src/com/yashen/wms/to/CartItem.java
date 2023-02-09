package com.yashen.wms.to;

public class CartItem {
    private int no;
    private String pId;
    private String name;
    private double price;
    private int qty;
    private int rowTotal;

    public CartItem(int no, String pId, String name, double price, int qty,int rowTotal) {
        this.no = no;
        this.pId = pId;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.rowTotal = rowTotal;
    }

    public int getRowTotal() {
        return rowTotal;
    }

    public void setRowTotal(int rowTotal) {
        this.rowTotal = rowTotal;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
