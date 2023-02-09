package com.yashen.wms.model;

import com.yashen.wms.to.CartItem;
import com.yashen.wms.to.Product;
import com.yashen.wms.to.ProductBoxModel;
import com.yashen.wms.util.CrudUtil;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MakeOrderModel {
    public static ArrayList<Product> productList = new ArrayList();
    public static ArrayList<CartItem> cartList = new ArrayList<>();
    public static ResultSet rst;

    public static ArrayList<Product> getProducts(String type) throws SQLException, ClassNotFoundException {
        System.out.println("ch-p003");
        productList.clear();
        if (type.equalsIgnoreCase("laptop")) {
            rst = CrudUtil.execute("SELECT * FROM product WHERE typeOfProduct = ?", "laptop");
        } else if (type.equalsIgnoreCase("vga")) {
            rst = CrudUtil.execute("SELECT * FROM product WHERE typeOfProduct = ?", "vga");
            System.out.println("2nd time vga clicked...");
        } else if (type.equalsIgnoreCase("monitor")) {
            rst = CrudUtil.execute("SELECT * FROM product WHERE typeOfProduct = ?", "monitor");
        } else if (type.equalsIgnoreCase("motherBoard")) {
            rst = CrudUtil.execute("SELECT * FROM product WHERE typeOfProduct = ?", "mb");
        } else {
            rst = CrudUtil.execute("SELECT * FROM product");
        }
        System.out.println("ch-p004");
        while (rst.next()) {
            String id = rst.getString(1);
            String name = rst.getString(2);
            String brand = rst.getString(3);
            String itemType = rst.getString(4);
            double price = Double.parseDouble(String.valueOf(rst.getDouble(5)));
            String color = "yellow";
            /*System.out.println(id+" "+name+" "+brand+" "+price);*/
            productList.add(new Product(id, name, brand, itemType, price));
        }
        System.out.println("ch-p001");
        for (Product p : productList) {
            System.out.println(p.getId() + " " + p.getName() + " " + p.getBrand() + " " + p.getPrice());
        }
        System.out.println("ch-p002");
        return productList;
    }

    public static ProductBoxModel getProductDataToMainContext(String pid) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM product WHERE p_id = ?", pid);
        if (rst.next()) {
            String id = rst.getString(1);
            String name = rst.getString(2);
            String brand = rst.getString(3);
            String itemType = rst.getString(4);
            double price = Double.parseDouble(String.valueOf(rst.getDouble(5)));
            return new ProductBoxModel(id, name, brand, itemType, price, "yellow");
        }
        return null;
    }

    public static void setToCart(Product product, int qty) {
        int alreadyExistsNumber = isAlreadyExists(product.getId());
        if (alreadyExistsNumber == -1){
            int len = cartList.size() + 1;
            CartItem cartItem = new CartItem(len, product.getId(), product.getName(), product.getPrice(), qty, (int) (product.getPrice() * qty));
            boolean add = cartList.add(cartItem);
            if (add) {
                new Alert(Alert.AlertType.CONFIRMATION, "successfully added new item to your cart").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "sorry... can't add this one to cart").show();
            }
            System.out.println("added new data to cart...");
        }else{
            int tempQty = cartList.get(alreadyExistsNumber).getQty()+qty;
            double tempRowTotal = cartList.get(alreadyExistsNumber).getRowTotal()+(cartList.get(alreadyExistsNumber).getPrice()*qty);
            cartList.get(alreadyExistsNumber).setQty(tempQty);
            cartList.get(alreadyExistsNumber).setRowTotal((int) tempRowTotal);
        }



        for (CartItem c : cartList) {
            System.out.println(c.getNo() + " " + c.getName() + " " + c.getQty());
        }
    }

    private static int isAlreadyExists(String id) {
        for (int i = 0; i < cartList.size(); i++) {
            if (cartList.get(i).getpId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public static int setQty(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT SUM(qty_on_hand) FROM product_stoke WHERE p_id = ?", id);
        if (rst == null){
            return 0;
        }
        String qty = null;
        while (rst.next()) {
            return rst.getInt(1);

        }
        return 0;
    }
}
