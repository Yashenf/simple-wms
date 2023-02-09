package com.yashen.wms.model;

import com.yashen.wms.to.Stoke;
import com.yashen.wms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StokeModel {
    public static ArrayList<Stoke> getData(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Stoke> stokeList = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM product_stoke WHERE p_id = ?", id);
        while(rst.next()){
            stokeList.add(new Stoke(rst.getString(1), rst.getInt(2), rst.getString(3) ));
        }
        return stokeList;
    }

    public static boolean updateQty(Stoke stoke) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE product_stoke SET qty_on_hand = ? WHERE p_id = ? AND store_id = ?", stoke.getQty(), stoke.getpId(), stoke.getsId());
    }

    public static int getTotQtyOfProduct(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT SUM(qty_on_hand) FROM product_stoke WHERE p_id =?", id);
        while (rst.next()){
            return rst.getInt(1);
        }
        return 0;
    }
}
