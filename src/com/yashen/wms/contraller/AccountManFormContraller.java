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

public class AccountManFormContraller implements Initializable {

    @FXML
    private AnchorPane landingContext;

    @FXML
    void signUpBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.SIGN_UP,landingContext);
    }

    @FXML
    void updateBtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ACC_UPDATE,landingContext);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Parent load = null;
        try {
            load = FXMLLoader.load(getClass().getResource("/com/yashen/wms/view/account/AccountMainView.fxml"));
            landingContext.getChildren().clear();
            landingContext.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
