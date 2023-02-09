package com.yashen.wms.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage)Navigation.pane.getScene().getWindow();

        switch (route) {
            case MAIN_FORM:
                initUI("MainViewForm.fxml");
                break;
            case ADMIN_MAIN_FORM:
                initUI("AdminMainForm.fxml");
                break;
            case ADMIN_BUSINESS_FUNCTIONS:
                initUI("AdminBusinessForm.fxml");
                break;
            case CUSTOMER:
                initUI("CustomerForm.fxml");
                break;
            case ADD_CUSTOMER:
                initUI("customer/AddCustomerForm.fxml");
                break;
            case SEARCH_CUSTOMERS:
                initUI("customer/SearchCustomerForm.fxml");
                break;
            case VIEW_CUSTOMER:
                initUI("customer/ViewAllCustomersForm.fxml");
                break;
            case ORDERS:
                initUI("OrderMainForm.fxml");
                break;
            case MAKE_ORDER:
                initUI("orders/MakeOrder.fxml");
                break;
            case PLACE_ORDER:
                initUI("orders/PlaceOrderForm.fxml");
                break;
            case VIEW_ORDERS:
                initUI("orders/ViewOrdersForm.fxml");
                break;
            case INERNAL_MAIN_VIEW:
                initUI("InternalFunctionsMainViewForm.fxml");
                break;
            case PRODUCTS_MAIN_VIEW:
                initUI("product/ProductMainForm.fxml");
                break;
            case ADD_NEW_PRODUCT:
                initUI("product/AddProductForm.fxml");
                break;
            case STORE_MAIN_VIEW:
                initUI("StoreMainForm.fxml");
                break;
            case MANAGE_STORE_QTY:
                initUI("Stores/ManageStokesForm.fxml");
                break;
            case STORE_CRUD:
                initUI("Stores/StoreForm.fxml");
                break;
            case VIEW_PRODUCT:
                initUI("product/ViewProductsForm.fxml");
                break;
            case ACC_MAIN:
                initUI("AccountManForm.fxml");
                break;
            case SIGN_UP:
                initUI("account/AccountMainView.fxml");
                break;
            case ACC_UPDATE:
                initUI("account/UpdateAccountForm.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
        }
    }
    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/com/yashen/wms/view/" + location)));
    }
}
