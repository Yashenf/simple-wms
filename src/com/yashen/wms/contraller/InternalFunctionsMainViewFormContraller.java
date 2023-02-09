package com.yashen.wms.contraller;

import com.yashen.wms.util.Navigation;
import com.yashen.wms.util.Routes;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InternalFunctionsMainViewFormContraller {

    @FXML
    private AnchorPane context;

    @FXML
    void ManageProductOnAction(MouseEvent event) {

    }

    @FXML
    void manageProductsOnAction(MouseEvent event) throws IOException {
        Navigation.navigate(Routes.PRODUCTS_MAIN_VIEW,context);
    }

    @FXML
    void manageShippingAgencyOnAction(MouseEvent event) {

    }

    @FXML
    void manageShippingOnAction(MouseEvent event) {

    }

    @FXML
    void manageUserAccountOnAction(MouseEvent event) throws IOException {
        Navigation.navigate(Routes.ACC_MAIN,context);
    }

}
