package com.yashen.wms.to;

public class Store {
    private int no;
    private String storeId;
    private String location;

    public Store() {
    }

    public Store(int no, String storeId, String location) {
        this.no = no;
        this.storeId = storeId;
        this.location = location;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
