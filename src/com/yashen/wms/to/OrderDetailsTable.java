package com.yashen.wms.to;

public class OrderDetailsTable {
    private int no;
    private String orderId;
    private String pId;
    private int qty;
    private double rowTotal;

    public OrderDetailsTable(int no, String orderId, String pId, int qty, double rowTotal) {
        this.no = no;
        this.orderId = orderId;
        this.pId = pId;
        this.qty = qty;
        this.rowTotal = rowTotal;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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
