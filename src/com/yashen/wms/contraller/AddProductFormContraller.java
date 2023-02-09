package com.yashen.wms.contraller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.yashen.wms.model.ProductModel;
import com.yashen.wms.to.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddProductFormContraller implements Initializable {

    @FXML
    private Label productIdLbl;

    @FXML
    private JFXTextField productNameTxt;

    @FXML
    private JFXTextField barndTxt;

    @FXML
    private JFXComboBox<String> productTypeCmb;

    @FXML
    private JFXTextField priceTxt;

    @FXML
    void saveBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (isNotEmpty()){
            String pId = productIdLbl.getText();
            String name = productNameTxt.getText();
            String brand = barndTxt.getText();
            String type = productTypeCmb.getValue();
            double price = Double.parseDouble(priceTxt.getText());
            boolean b = ProductModel.saveProduct(new Product(pId, name, brand, type, price));

            if (b){
                new Alert(Alert.AlertType.INFORMATION,"successfully save new product").showAndWait();
            }else {
                new Alert(Alert.AlertType.INFORMATION,"try again!").showAndWait();
            }
        }
    }

    private boolean isNotEmpty() {
        if (productNameTxt.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"please enter product id ").showAndWait();
            return false;
        }else if(barndTxt.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"please enter brand name ").showAndWait();
            return false;
        }else if(priceTxt.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"please enter product price ").showAndWait();
            return false;
        }else if(productTypeCmb.getValue().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"please enter product type ").showAndWait();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productTypeCmb.getItems().addAll("vga","laptop","motherBoard","monitor");
        setNextProductId();
    }

    private void setNextProductId() {
        try {
            String oldProductId = ProductModel.getNextProductId();
            if (oldProductId == null){
                oldProductId="P0001";
            }else{
                String[] split = oldProductId.split("[P]");
                System.out.println(split[0]+" , ");
                int lastDigit = Integer.parseInt(split[1]);
                String newOrderId = String.format("P%04d",++lastDigit);
                productIdLbl.setText(newOrderId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
