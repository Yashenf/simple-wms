package com.yashen.wms.contraller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.yashen.wms.model.CustomerModel;
import com.yashen.wms.to.Address;
import com.yashen.wms.to.Customer;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class SearchCustomerFormContraller {

    @FXML
    private JFXComboBox<String> customerIdCmb;

    @FXML
    private JFXTextField customerNameTxt;

    @FXML
    private JFXTextField tpNoTxt;

    @FXML
    private JFXTextField emailTxt;

    @FXML
    private JFXTextField noTxt;

    @FXML
    private JFXTextField streetTxt;

    @FXML
    private JFXTextField cityTxt;

    @FXML
    private JFXTextField stateTxt;

    @FXML
    private JFXTextField countryTxt;

    @FXML
    private JFXTextField zipTxt;

    @FXML
    void customerIdCmbOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        Customer selectedCustomer = CustomerModel.getSelectedCustomer(customerIdCmb.getValue());
        /*String address = selectedCustomer.getAddress().getNo()+" \n"+selectedCustomer.getAddress().getStreet()+" \n"+selectedCustomer.getAddress().getCity()+" \n"+
                selectedCustomer.getAddress().getState()+" \n"+selectedCustomer.getAddress().getCountry()+" \n"+selectedCustomer.getAddress().getZip();
        noTxt.setText(selectedCustomer.getAddress().getNo());*/
        streetTxt.setText(selectedCustomer.getAddress().getStreet());
        cityTxt.setText(selectedCustomer.getAddress().getCity());
        stateTxt.setText(selectedCustomer.getAddress().getState());
        countryTxt.setText(selectedCustomer.getAddress().getCountry());
        zipTxt.setText(selectedCustomer.getAddress().getZip());
        tpNoTxt.setText(selectedCustomer.getTp());

        customerNameTxt.setText(selectedCustomer.getName());
        tpNoTxt.setText(selectedCustomer.getTp());
        emailTxt.setText(selectedCustomer.getEmail());
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure ?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get() == ButtonType.YES && !customerIdCmb.getValue().isEmpty()){
            boolean b = CustomerModel.deleteCustomer(customerIdCmb.getValue());
            if (b){
                new Alert(Alert.AlertType.INFORMATION,"successfully deleted customer").showAndWait();
                clearFields();
                loardCmb();
            }else{
                new Alert(Alert.AlertType.INFORMATION,"try again!").showAndWait();
            }
        }
    }

    @FXML
    void updateBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure ?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get() == ButtonType.YES && !customerIdCmb.getValue().isEmpty()){
            Customer customer = new Customer(customerIdCmb.getValue(),customerNameTxt.getText(),tpNoTxt.getText(),emailTxt.getText(),
                    new Address(noTxt.getText(),streetTxt.getText(),cityTxt.getText(),stateTxt.getText(),countryTxt.getText(),zipTxt.getText()));
            boolean b = CustomerModel.updateCustomer(customer);
            if (b){
                new Alert(Alert.AlertType.INFORMATION,"successfully updated account").showAndWait();
                clearFields();
            }else{
                new Alert(Alert.AlertType.INFORMATION,"try again!").showAndWait();
            }
        }
    }


    public void initialize() {
        try {
            loardCmb();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void loardCmb() throws SQLException, ClassNotFoundException {
        if (!customerIdCmb.getItems().isEmpty()){
            customerIdCmb.getItems().clear();
        }
        ArrayList<Customer> allCustomers = CustomerModel.getAllCustomers();
        for (Customer c : allCustomers){
            customerIdCmb.getItems().add(c.getcId());
        }
        customerIdCmb.setValue(allCustomers.get(0).getcId());
        customerIdCmbOnAction( new ActionEvent());
    }

    private void clearFields(){
        zipTxt.clear();
        tpNoTxt.clear();
        countryTxt.clear();
        noTxt.clear();
        customerNameTxt.clear();
        stateTxt.clear();
        cityTxt.clear();
        emailTxt.clear();
        streetTxt.clear();
    }
}
