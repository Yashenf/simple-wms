package com.yashen.wms.contraller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.yashen.wms.model.ProductModel;
import com.yashen.wms.to.Product;
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

public class ViewProductsFormContraller implements Initializable {

    public TableView<Product> productTbl;
    public Label pIdLbl;
    @FXML
    private TableColumn<?, ?> pIdCol;

    @FXML
    private TableColumn<?, ?> descCol;

    @FXML
    private TableColumn<?, ?> brandCol;

    @FXML
    private TableColumn<?, ?> priceCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private JFXTextField productNameTxt;

    @FXML
    private JFXTextField barndTxt;

    @FXML
    private JFXComboBox<String> productTypeCmb;

    @FXML
    private JFXTextField priceTxt;

    @FXML
    void deleteBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        // validate this
        boolean b = ProductModel.deleteProduct(pIdLbl.getText());
        if (b){
            new Alert(Alert.AlertType.INFORMATION,"successfully deleted product", ButtonType.OK).showAndWait();
        }else{
            new Alert(Alert.AlertType.INFORMATION,"try again!", ButtonType.OK).showAndWait();
        }
    }

    @FXML
    void updateBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean flag = ProductModel.updateProduct(new Product(pIdLbl.getText(),productNameTxt.getText(),barndTxt.getText(),
                productTypeCmb.getValue(),Double.parseDouble(priceTxt.getText())));
        if (flag){
            new Alert(Alert.AlertType.INFORMATION,"successfully updated product", ButtonType.OK).showAndWait();
        }else{
            new Alert(Alert.AlertType.INFORMATION,"try again!", ButtonType.OK).showAndWait();
        }

    }
    private void setDataForMainTable() throws SQLException, ClassNotFoundException {
        ArrayList<Product> allProducts = ProductModel.getAllData();
        ObservableList<Product> allProductsObList = FXCollections.observableArrayList();
        allProductsObList.addAll(allProducts);
        productTbl.setItems(allProductsObList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("itemType"));
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productTypeCmb.getItems().addAll("vga","laptop","motherBoard","monitor");
        try {
            setDataForMainTable();
            productTbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                barndTxt.setText(newValue.getBrand());
                priceTxt.setText(String.valueOf(newValue.getPrice()));
                productNameTxt.setText(newValue.getName());
                productTypeCmb.setValue(newValue.getItemType());
                pIdLbl.setText(newValue.getId());
            });
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
