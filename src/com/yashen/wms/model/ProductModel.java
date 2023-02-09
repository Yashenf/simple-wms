package com.yashen.wms.model;

import com.yashen.wms.DB.DBConnection;
import com.yashen.wms.to.Product;
import com.yashen.wms.util.CrudUtil;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductModel {

    public static String getNextProductId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT p_id FROM product ORDER BY(p_id)DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    public static boolean saveProduct(Product product) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO product VALUES (?,?,?,?,?)", product.getId(), product.getName(), product.getBrand(),
                product.getItemType(), product.getPrice());

    }

    public static boolean updateQty(String pId,int qty) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM product_stoke WHERE p_id = ?", pId);
        int orderQty = qty;
        while (rst.next()){
            int old_qty_on_hand = rst.getInt(2);
            String store_id = rst.getString(3);
            int newQtyOnHand = old_qty_on_hand-orderQty;
            if (newQtyOnHand<=0 && orderQty>0){
                boolean flag = CrudUtil.execute("UPDATE product_stoke SET qty_on_hand = ? WHERE p_id = ? AND store_id = ?", 0, pId, store_id);
                System.out.println("old qty :-"+old_qty_on_hand +"   "+"new qty  :-"+newQtyOnHand);
                if (flag){
                    orderQty-=old_qty_on_hand;
                    System.out.println(" after updated old qty :-"+old_qty_on_hand +"   "+"new qty  :-"+newQtyOnHand);
                }
            }else{
                boolean flag = CrudUtil.execute("UPDATE product_stoke SET qty_on_hand = ? WHERE p_id = ? AND store_id = ?", newQtyOnHand, pId, store_id);
                System.out.println("ending old qty :-"+old_qty_on_hand +"   "+"new qty  :-"+newQtyOnHand);
                if (flag){
                   return true;
                }
            }

        }
        return false;
    }

    public static ArrayList<Product> getAllData() throws SQLException, ClassNotFoundException {
        ArrayList<Product> productList = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM product");
        while(rst.next()){
            productList.add(new Product(rst.getString(1), rst.getString(2), rst.getString(3),
                    rst.getString(4), rst.getDouble(5)));
        }
        return productList;
    }

    public static boolean deleteProduct(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM product WHERE p_id = ?", id);
    }

    public static boolean updateProduct(Product product) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE product SET  p_name =? , brand =? , typeOfProduct = ? , price = ? WHERE p_id = ?",
                product.getName(),product.getBrand(),product.getItemType(),product.getPrice(),product.getId());
    }
    /*UPDATE table_name
SET column_1 = expr_1,
    column_2 = expr_2,
    ...
    column_n = expr_n
WHERE conditions;  */
}
/* private String id;
    private String name;
    private String brand;
    private String itemType;
    private double price;*/