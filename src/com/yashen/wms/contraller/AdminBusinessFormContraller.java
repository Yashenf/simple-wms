package com.yashen.wms.contraller;

import com.yashen.wms.util.Navigation;
import com.yashen.wms.util.Routes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminBusinessFormContraller {
    public AnchorPane adminBusinessContext;

    public void manageCustomerOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER,adminBusinessContext);
    }

    public void manageOrderOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.ORDERS,adminBusinessContext);
    }

    public void managePackageOnAction(MouseEvent mouseEvent) {
    }

    public void manageShippingOnAction(MouseEvent mouseEvent) {
    }

    public void manageStoreOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.STORE_MAIN_VIEW,adminBusinessContext);
    }
}
