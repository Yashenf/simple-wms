package com.yashen.wms.contraller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.yashen.wms.model.ProductModel;
import com.yashen.wms.model.StokeModel;
import com.yashen.wms.model.StoreModel;
import com.yashen.wms.to.Product;
import com.yashen.wms.to.Stoke;
import com.yashen.wms.to.Store;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageStokesFormContraller implements Initializable {

    public TableColumn<Object, Object> ptCol;
    public TableColumn<Object, Object> stCol;
    public Stoke selectedStoke;
    @FXML
    private TableView<Product> productTbl;

    @FXML
    private TableColumn<?, ?> proIdCol;

    @FXML
    private TableColumn<?, ?> proNameCol;

    @FXML
    private TableColumn<?, ?> proTypeCol;

    @FXML
    private TableColumn<?, ?> proBrandCol;

    @FXML
    private JFXTextField saveQtyTxt;

    @FXML
    private JFXComboBox<String> storeIdCmb;

    @FXML
    private Label productNameLbl;

    @FXML
    private Label productIdLbl;

    @FXML
    private TableView<Stoke> stokeTbl;

    @FXML
    private TableColumn<Stoke, String> pCol;

    @FXML
    private TableColumn<?, ?> qohCol;

    @FXML
    private TableColumn<?, ?> sCol;

    @FXML
    private JFXTextField qtyUpdateTxt;

    @FXML
    void addNewStokeOnAction(ActionEvent event) {

    }

    @FXML
    void updateStokeBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean b = StokeModel.updateQty(new Stoke(selectedStoke.getpId(), Integer.parseInt(qtyUpdateTxt.getText()), selectedStoke.getsId()));
        if (b){
            new Alert(Alert.AlertType.INFORMATION,"successfully updated stoke", ButtonType.OK).showAndWait();
        }else{
            new Alert(Alert.AlertType.INFORMATION,"try again!", ButtonType.OK).showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setDataForMainTable();
            pCol.setCellValueFactory(new PropertyValueFactory<>("pId"));
            qohCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
            sCol.setCellValueFactory(new PropertyValueFactory<>("sId"));
            proIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            proNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            proTypeCol.setCellValueFactory(new PropertyValueFactory<>("itemType"));
            proBrandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setDataForMainTable() throws SQLException, ClassNotFoundException {
        ArrayList<Product> allProducts = ProductModel.getAllData();
        ObservableList<Product> allProductsObList = FXCollections.observableArrayList();
        allProductsObList.addAll(allProducts);
        productTbl.setItems(allProductsObList);
        productTbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setDataForStokeTable(newValue.getId());
                productIdLbl.setText(newValue.getId());
                productNameLbl.setText(newValue.getName());
                setTotQty(productIdLbl.getText());
                setCorrectStores();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setCorrectStores() throws SQLException, ClassNotFoundException {
        ArrayList<Store> allStores = StoreModel.getAllStores();
        for (Store s : allStores){
            storeIdCmb.getItems().add(s.getStoreId());
        }
    }

    private void setTotQty(String pId) throws SQLException, ClassNotFoundException {
        int totQtyOfProduct = StokeModel.getTotQtyOfProduct(pId);
        saveQtyTxt.setText(String.valueOf(totQtyOfProduct));
        System.out.println(String.valueOf(totQtyOfProduct));
    }

    private void setDataForStokeTable(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Stoke> data = StokeModel.getData(id);
        ObservableList<Stoke> stokeObList = FXCollections.observableArrayList();
        stokeObList.addAll(data);
        stokeTbl.setItems(stokeObList);
        stokeTbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            qtyUpdateTxt.setText(String.valueOf(newValue.getQty()));
            selectedStoke = newValue;
        });

    }
}
/*  private String id;
    private String name;
    private String brand;
    private String itemType;
    private double price;*/