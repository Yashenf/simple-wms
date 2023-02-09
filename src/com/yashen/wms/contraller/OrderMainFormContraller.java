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

public class OrderMainFormContraller implements Initializable {

    public AnchorPane landingContext;

    public void makeOrderOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAKE_ORDER,landingContext);
    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PLACE_ORDER,landingContext);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Parent load = null;
        try {
            load = FXMLLoader.load(getClass().getResource("/com/yashen/wms/view/orders/MakeOrder.fxml"));
            landingContext.getChildren().clear();
            landingContext.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void viewOrders(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.VIEW_ORDERS, landingContext);
    }
}
