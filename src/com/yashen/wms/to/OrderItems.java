package com.yashen.wms.to;

public class OrderItems {
    private String orderId;
    private String pId;
    private int qty;
    private double rowTotal;

    public OrderItems() {
    }

    public OrderItems(String orderId, String pId, int qty, double rowTotal) {
        this.orderId = orderId;
        this.pId = pId;
        this.qty = qty;
        this.rowTotal = rowTotal;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getRowTotal() {
        return rowTotal;
    }

    public void setRowTotal(double rowTotal) {
        this.rowTotal = rowTotal;
    }
}
