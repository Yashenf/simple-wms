package com.yashen.wms.contraller;

import com.yashen.wms.util.Navigation;
import com.yashen.wms.util.Routes;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerFormContraller implements Initializable {
    public AnchorPane customerSubContext;



    public void addCustomerBtnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADD_CUSTOMER,customerSubContext);
    }

    public void viewCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.VIEW_CUSTOMER,customerSubContext);
    }

    public void updateCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCH_CUSTOMERS,customerSubContext);
    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCH_CUSTOMERS,customerSubContext);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Parent load = null;
        try {
            load = FXMLLoader.load(getClass().getResource("/com/yashen/wms/view/customer/AddCustomerForm.fxml"));
            customerSubContext.getChildren().clear();
            customerSubContext.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
