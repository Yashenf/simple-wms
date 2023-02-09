package com.yashen.wms.contraller;

import com.yashen.wms.model.CustomerModel;
import com.yashen.wms.to.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewAllCustomersFormContraller implements Initializable {

    public TableView customerTbl;
    @FXML
    private TableColumn<?, ?> cIdCol;

    @FXML
    private TableColumn<?, ?> cNameCol;

    @FXML
    private TableColumn<?, ?> cTpCol;

    @FXML
    private TableColumn<?, ?> cEmailCol;

    @FXML
    private TableColumn<?, ?> cAddCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            cIdCol.setCellValueFactory(new PropertyValueFactory<>("cId"));
            cNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            cTpCol.setCellValueFactory(new PropertyValueFactory<>("tp"));
            cEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            cAddCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            ArrayList<Customer> allCustomers = CustomerModel.getAllCustomers();
            ObservableList<Customer> customerObList = FXCollections.observableArrayList();
            customerObList.addAll(allCustomers);
            customerTbl.setItems(customerObList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
/* private String cId;
    private String name;
    private String tp;
    private String email;
    private Address address;*/