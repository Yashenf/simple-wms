package com.yashen.wms.contraller;

import com.yashen.wms.util.Navigation;
import com.yashen.wms.util.Routes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductMainFormOnAction implements Initializable {

    @FXML
    private AnchorPane landingContect;

    @FXML
    void addNewProductOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ADD_NEW_PRODUCT,landingContect);
    }

    @FXML
    void updateOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEW_PRODUCT,landingContect);
    }

    @FXML
    void viewProductOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEW_PRODUCT,landingContect);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Parent load = null;
        try {
            load = FXMLLoader.load(getClass().getResource("/com/yashen/wms/view/product/AddProductForm.fxml"));
            landingContect.getChildren().clear();
            landingContect.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
