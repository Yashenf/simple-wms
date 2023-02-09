package com.yashen.wms.contraller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.yashen.wms.model.CustomerModel;
import com.yashen.wms.to.Address;
import com.yashen.wms.to.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jdk.internal.dynalink.support.Guards.isNull;

public class AddCustomerFormContraller implements Initializable {

    @FXML
    private JFXTextField cusIdTxt;

    @FXML
    private JFXTextField cusNameTxt;

    @FXML
    private JFXTextField cusTpTxt;

    @FXML
    private JFXTextField cusEmailTxt;

    @FXML
    private JFXComboBox<String> countryCmb;

    @FXML
    private JFXComboBox<String> stateCmb;

    @FXML
    private JFXComboBox<String> cityCmb;

    @FXML
    private JFXComboBox<String> zipCmb;

    @FXML
    private JFXTextField noTxt;

    @FXML
    private JFXTextField streetTxt;

    @FXML
    void submitBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean b = regexValidation();
        if (b) {
            String cId = cusIdTxt.getText().trim();
            Address address = setAddress();
            String name = cusNameTxt.getText().trim();
            String tp = cusTpTxt.getText().trim();
            String email = cusEmailTxt.getText().trim();

            boolean b1 = CustomerModel.saveNewCustomer(new Customer(cId, name, tp, email, address));
            if (b1){
                new Alert(Alert.AlertType.INFORMATION,"successfully register new customer", ButtonType.OK).showAndWait();
            }
        }
    }


    private Address setAddress() {
        String no = noTxt.getText().trim();
        String street = streetTxt.getText().trim();
        String city = cityCmb.getValue();
        String state = stateCmb.getValue();
        String country = countryCmb.getValue();
        String zip = zipCmb.getValue();

        return new Address(no, street, city, state, country, zip);
    }

    private boolean regexValidation() {
        Pattern emailPatern = Pattern.compile("^(.+)@(.+)$");
        Matcher emailMatcher = emailPatern.matcher(cusEmailTxt.getText().trim());

        /*Pattern tpPatern = Pattern.compile("/^([2-9]{1})([0-9]{2})([\\s.-]?)([2-9]{1})([0-9]{2})([\\s.-]?)([0-9]{4})$/gm");
        Matcher tpMatcher = emailPatern.matcher(cusEmailTxt.getText().trim());*/

        if (!emailMatcher.find()) {
            new Alert(Alert.AlertType.ERROR, "Check your email again").showAndWait();
            return false;
        }
        /*if (!emailMatcher.find()) {
            new Alert(Alert.AlertType.ERROR, "Check your phone number again").showAndWait();
            return false;
        }*/
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("start initialize");
        initializeCmbs();
        System.out.println("end initialize");
        try {
            setNextCustomerId();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setNextCustomerId() throws SQLException, ClassNotFoundException {
        String nextCustomerId = CustomerModel.getNextCustomerId();
        String[] customerId = nextCustomerId.split("[C]");
        int lastNumber = Integer.parseInt(customerId[1])+1;
        String newId = String.format("C%03d",lastNumber);
        cusIdTxt.setText(newId);
    }

    private void initializeCmbs() {
        countryCmb.getItems().addAll("USA", "Canada");
        countryCmb.setValue("USA");
        System.out.println("chp001");
        countryCmb.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (countryCmb.getValue().equalsIgnoreCase("USA")) {
                stateCmb.getItems().addAll("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina",
                        "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont");
                System.out.println("chp002");
            } else {
                stateCmb.getItems().addAll("Alberta", "British Columbia", "Manitoba", "New Brunswick", "Newfoundland and Labrador",
                        "Nova Scotia", "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan");
                System.out.println("chp003");
            }
        });

        System.out.println("chp004");
        cityCmb.getItems().addAll("city01", "city02", "city03");
        System.out.println("chp005");
        zipCmb.getItems().addAll("11056", "11057", "11058", "11059", "11060", "11051", "11062", "11063", "11064", "11046", "11066", "11076", "11052", "11051", "11050");
        System.out.println("chp006");
    }
}
