package com.yashen.wms.model;

import com.yashen.wms.to.Store;
import com.yashen.wms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StoreModel {
    public static ArrayList<Store> getAllStores() throws SQLException, ClassNotFoundException {
        ArrayList<Store> storeList = new ArrayList();
        ResultSet rst = CrudUtil.execute("SELECT * FROM store");
        int i = 0;
        while(rst.next()){
            storeList.add(new Store(i++,rst.getString(1), rst.getString(2)));
        }
        return storeList;
    }

    public static Store getSelectedStore(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM store WHERE store_id = ?", id);
        while(rst.next()){
            return new Store(0, rst.getString(1), rst.getString(2));
        }
        return null;
    }

    public static boolean deleteData(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM store WHERE store_id =? ", id);
    }

    public static boolean saveStore(Store store) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO store VALUES(?,?)", store.getStoreId(), store.getLocation());
    }

    public static String getNextStoreId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT store_id FROM store ORDER BY (store_id)DESC LIMIT 1");
        while (rst.next()){
            return rst.getString(1);
        }
        return "st000";
    }

    public static boolean updateStore(String location , String id) throws SQLException, ClassNotFoundException {
        System.out.println("called updateStore with "+id+" , "+location);
        System.out.println("quary is : "+"UPDATE store SET loaction = "+location+" WHERE store_id = "+id);
        return CrudUtil.execute("UPDATE store SET loaction = ? WHERE store_id = ?", location, id);
    }

    public static ArrayList<String> getExistsStore(String pId) throws SQLException, ClassNotFoundException {
        ArrayList<String> sIdList = new ArrayList();
        ResultSet rst = CrudUtil.execute("select DISTINCT (store_id) from product_stoke where p_id =?", pId);
        while (rst.next()){
            sIdList.add(rst.getString(1));
        }
        return sIdList;
    }
}
/*DELETE FROM table_name [WHERE Clause]*/