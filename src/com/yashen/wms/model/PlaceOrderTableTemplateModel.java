package com.yashen.wms.model;

import com.yashen.wms.to.CartItem;
import com.yashen.wms.view.tableModels.PlaceOrderTabelModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class PlaceOrderTableTemplateModel {

/*private int no;
    private String pId;
    private String name;
    private double price;
    private int qty;
    private int rowTotal;
    public Button btn;*/
    public static ObservableList<PlaceOrderTabelModel>setPlaceOrderCart() {
        ObservableList<PlaceOrderTabelModel> cartList = FXCollections.observableArrayList();
        for (CartItem cartItem : MakeOrderModel.cartList) {
            Button btn = new Button("Delete");
            cartList.add(new PlaceOrderTabelModel(cartItem.getNo(), cartItem.getpId(),
                    cartItem.getName(), cartItem.getPrice(), cartItem.getQty(), cartItem.getRowTotal(), btn));

        }
        return cartList;
    }
}
