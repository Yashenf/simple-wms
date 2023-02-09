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

public class StoreMainFormCopntraller implements Initializable {

    @FXML
    private AnchorPane landingContext;

    @FXML
    void addStoresOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.STORE_CRUD,landingContext);
    }

    @FXML
    void manageStoresOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.MANAGE_STORE_QTY,landingContext);
    }

    @FXML
    void viewStokesOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Parent load = null;
        try {
            load = FXMLLoader.load(getClass().getResource("/com/yashen/wms/view/Stores/StoreForm.fxml"));
            landingContext.getChildren().clear();
            landingContext.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
