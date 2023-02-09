package com.yashen.wms.contraller;

import com.yashen.wms.DB.DBConnection;
import com.yashen.wms.model.PlaceOrderModel;
import com.yashen.wms.to.Order;
import com.yashen.wms.to.OrderDetailsTable;
import com.yashen.wms.to.OrderTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ViewOrdersFormContraller implements Initializable {

    @FXML
    private TableView<OrderTable> orderTbl;

    @FXML
    private TableColumn<?, ?> orderNoCol;

    @FXML
    private TableColumn<?, ?> orderIdCol;

    @FXML
    private TableColumn<?, ?> customerCol;

    @FXML
    private TableColumn<?, ?> orderDateCol;

    @FXML
    private TableColumn<?, ?> lsOrderTotalCol;

    @FXML
    private TableView<OrderDetailsTable> OrderDetailsTbl;

    @FXML
    private TableColumn<?, ?> detailNoCol;

    @FXML
    private TableColumn<?, ?> productIdCol;

    @FXML
    private TableColumn<?, ?> qtyCol;

    @FXML
    private TableColumn<?, ?> rowTotCol;

    @FXML
    private Label orderIdLbl;

    @FXML
    private Label customerLbl;

    @FXML
    private Label discountLbl;

    @FXML
    private Label dateLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setDataToOrderTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setDataToOrderTable() throws SQLException, ClassNotFoundException {
        ArrayList<Order> allOrders = PlaceOrderModel.getAllOrders();
        ObservableList<OrderTable> orderObList = FXCollections.observableArrayList();
        int no = 1;
        for (Order o :allOrders){
            orderObList.add(new OrderTable(no++,o.getOid(),o.getDate(),o.getCustomer(),o.getTotal(),o.getDiscount()));
        }

        orderNoCol.setCellValueFactory(new PropertyValueFactory<>("no"));
        orderIdCol.setCellValueFactory(new PropertyValueFactory<>("oid"));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customer"));
        orderDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        lsOrderTotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

        orderTbl.setItems(orderObList);

        orderTbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            orderIdLbl.setText(newValue.getOid());
            customerLbl.setText(newValue.getCustomer());
            dateLbl.setText(String.valueOf(newValue.getDate()));
            discountLbl.setText(String.valueOf(newValue.getDiscount()));

            try {
                setOrderDetais(newValue.getOid());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setOrderDetais(String id) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetailsTable> orderDetails = PlaceOrderModel.getOrderDetails(id);
        ObservableList<OrderDetailsTable> orderDetailsObList = FXCollections.observableArrayList();
        orderDetailsObList.addAll(orderDetails);
        detailNoCol.setCellValueFactory(new PropertyValueFactory<>("no"));
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("pId"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        rowTotCol.setCellValueFactory(new PropertyValueFactory<>("rowTotal"));

        OrderDetailsTbl.setItems(orderDetailsObList);
    }

    



    public void reprtGenarateOnAction(ActionEvent actionEvent) {
        InputStream inputStream = this.getClass().getResourceAsStream("/repots/Cherry_1.jrxml");
        HashMap<String,Object> hm = new HashMap();
        hm.put("Parameter1","saman");
        try {

            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null,DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void reportOnAction(MouseEvent mouseEvent) {
    }
}
