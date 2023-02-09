package com.yashen.wms.to;

public class Stoke {
    private String pId;
    private int qty;
    private String sId;

    public Stoke() {
    }

    public Stoke(String pId, int qty, String sId) {
        this.pId = pId;
        this.qty = qty;
        this.sId = sId;
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

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }
}
