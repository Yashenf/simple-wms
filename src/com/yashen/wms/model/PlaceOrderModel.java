package com.yashen.wms.model;

import com.yashen.wms.to.Order;
import com.yashen.wms.to.OrderDetailsTable;
import com.yashen.wms.to.OrderItems;
import com.yashen.wms.util.CrudUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderModel {
    public static ArrayList<String> getCustomerData() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT C_ID FROM customer");
        ArrayList<String> cusIdList = new ArrayList<>();
        while (rst.next()){
            cusIdList.add(rst.getString(1));
        }
        return cusIdList;
    }

    public static String getCustomrName(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT c_name FROM customer WHERE c_id = ?", id);
        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    public static String getLastOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT o_id FROM `order` ORDER BY (o_id) DESC LIMIT 1");
        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    public static boolean saveOrderItem(OrderItems orderItems) throws SQLException, ClassNotFoundException {
        String oId = orderItems.getOrderId();
        String pId = orderItems.getpId();
        int qty = orderItems.getQty();
        double rowTot = orderItems.getRowTotal();
        boolean flag = CrudUtil.execute("INSERT INTO order_details VALUES (?,?,?,?)", oId, pId, qty, rowTot);
        return flag;
    }

    public static void setNewOrder(String oId , String cId,double total,double discount) throws SQLException, ClassNotFoundException {
        CrudUtil.execute("INSERT INTO `order` VALUES (?,now(),?,?,?)",oId,cId,total,discount);
    }

    public static ArrayList<Order> getAllOrders() throws SQLException, ClassNotFoundException {
        ArrayList<Order> orderList = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM `order`");
        while(rst.next()){
            String oId = rst.getString(1);
            Date date = rst.getDate(2);
            String customer = rst.getString(3);
            double total= rst.getDouble(4);
            double discount = rst.getDouble(5);
            orderList.add(new Order(oId,date,customer,total,discount));
        }
        return orderList;
    }

    public static ArrayList<OrderDetailsTable> getOrderDetails(String id) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetailsTable> orderDetailsList = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM order_details WHERE o_id = ?", id);
        int i = 1;
        while (rst.next()){
            String oId = rst.getString(1);
            String pId = rst.getString(2);
            int qty = rst.getInt(3);
            double rowTotal = rst.getDouble(4);

            orderDetailsList.add(new OrderDetailsTable(i++,oId,pId,qty,rowTotal));
        }
        return orderDetailsList;
    }
}
