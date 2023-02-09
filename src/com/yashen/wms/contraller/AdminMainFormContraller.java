package com.yashen.wms.contraller;

import com.yashen.wms.util.Navigation;
import com.yashen.wms.util.Routes;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminMainFormContraller {

    public AnchorPane adminContext;
    public  AnchorPane context;
    public Label nameLbl;
    public Label accountLbl;


    public void initialize() throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/com/yashen/wms/view/AdminBusinessForm.fxml"));
        adminContext.getChildren().clear();
        adminContext.getChildren().add(load);
    }

    public void AdminBusinessBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_BUSINESS_FUNCTIONS,adminContext);
    }

    public void backToHomeBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAIN_FORM,context);
    }

    public void internalFunctionsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.INERNAL_MAIN_VIEW,adminContext);
    }

    public void reportsOnAction(ActionEvent actionEvent) {
    }

    public void helpOnAction(ActionEvent actionEvent) {
    }
}
