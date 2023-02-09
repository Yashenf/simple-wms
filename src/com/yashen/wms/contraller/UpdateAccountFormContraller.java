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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdateAccountFormContraller implements Initializable {

    @FXML
    private JFXTextField firstNameTxt;

    @FXML
    private JFXTextField lastNameTxt;

    @FXML
    private JFXPasswordField passwordTxt;

    @FXML
    private JFXTextField emailTxt;

    @FXML
    private JFXTextField mobileNoTxt;

    @FXML
    private JFXComboBox<String> accountTypeCmb;

    @FXML
    private JFXComboBox<String> userNameCmb;

    @FXML
    void deleteBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean b = AccountModel.deleteAccount(userNameCmb.getValue());
        if (b){
            new Alert(Alert.AlertType.INFORMATION,"successfully deleted account", ButtonType.OK).showAndWait();
        }else{
            new Alert(Alert.AlertType.INFORMATION,"try again!", ButtonType.OK).showAndWait();
        }
    }

    @FXML
    void updateBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean b = AccountModel.updateAccount(new Account(firstNameTxt.getText(), lastNameTxt.getText(), userNameCmb.getValue(), passwordTxt.getText(),
                emailTxt.getText(), mobileNoTxt.getText(), accountTypeCmb.getValue()));
        if (b){
            new Alert(Alert.AlertType.INFORMATION,"successfully updated", ButtonType.OK).showAndWait();
        }else{
            new Alert(Alert.AlertType.INFORMATION,"try again!", ButtonType.OK).showAndWait();
        }
    }

    public void userNameCmbOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Account user = AccountModel.getUser(userNameCmb.getValue());
        firstNameTxt.setText(user.getFirstName());
        lastNameTxt.setText(user.getLastname());
        passwordTxt.setText(user.getPassword());
        emailTxt.setText(user.getEmail());
        mobileNoTxt.setText(user.getMobile());
        accountTypeCmb.setValue(user.getAccType());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadUserNames();
            accountTypeCmb.getItems().addAll("Admin","Manager","Worker");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadUserNames() throws SQLException, ClassNotFoundException {
        ArrayList<String> userNames = AccountModel.getUserNames();
        for (String un :userNames){
            userNameCmb.getItems().add(un);
        }
    }
}
