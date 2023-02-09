package com.yashen.wms.contraller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.yashen.wms.model.AccountModel;
import com.yashen.wms.to.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountMainViewContraller implements Initializable {

    @FXML
    private JFXTextField firstNameTxt;

    @FXML
    private JFXTextField lastNameTxt;

    @FXML
    private JFXTextField userNameTxt;

    @FXML
    private JFXPasswordField passwordTxt;

    @FXML
    private JFXTextField emailTxt;

    @FXML
    private JFXTextField mobileNoTxt;

    @FXML
    private JFXComboBox<String> accountTypeCmb;

    @FXML
    void signUpBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean b = AccountModel.signUpAcc(new Account(firstNameTxt.getText(), lastNameTxt.getText(), userNameTxt.getText().trim(), passwordTxt.getText().trim(),
                emailTxt.getText(), mobileNoTxt.getText(), accountTypeCmb.getValue()));
        if (b){
            new Alert(Alert.AlertType.INFORMATION,"successfully registered", ButtonType.OK).showAndWait();
        }else{
            new Alert(Alert.AlertType.INFORMATION,"try again!", ButtonType.OK).showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountTypeCmb.getItems().addAll("Admin","Manager","Worker");
    }
}
