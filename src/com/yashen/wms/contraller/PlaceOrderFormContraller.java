package com.yashen.wms.contraller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.yashen.wms.DB.DBConnection;
import com.yashen.wms.model.PlaceOrderModel;
import com.yashen.wms.model.PlaceOrderTableTemplateModel;
import com.yashen.wms.model.ProductModel;
import com.yashen.wms.to.OrderItems;
import com.yashen.wms.view.tableModels.PlaceOrderTabelModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PlaceOrderFormContraller implements Initializable {

    Connection conn = null;
    public Label customerNameLbl;
    @FXML
    private TableView<PlaceOrderTabelModel> productTbl;

    @FXML
    private TableColumn<?, ?> noCol;

    @FXML
    private TableColumn<?, ?> productCol;

    @FXML
    private TableColumn<?, ?> priceCol;

    @FXML
    private TableColumn<?, ?> qtyCol;

    @FXML
    private TableColumn<?, ?> totalCol;

    @FXML
    private TableColumn<?, ?> moreCol;

    @FXML
    private Label orderIdLbl;

    @FXML
    private JFXComboBox<String> customerIdCmb;

    @FXML
    private Label customerNameCol;

    @FXML
    private JFXComboBox<?> productIdCmb;

    @FXML
    private JFXComboBox<?> qtyCmb;

    @FXML
    private Label subTotalLbl;

    @FXML
    private JFXTextField discountTxt;

    @FXML
    private Label paybleTotalLbl;
    private double subTotal = 0;
    private double paybleTotal = 0;
    public ObservableList<PlaceOrderTabelModel> placeOrderTabelModelsList;

    @FXML
    void discountTxtOnAction(ActionEvent event) {
        /*INSERT INTO `order` VALUES ('Ord001',now(),'c002');*/
        double discount = (subTotal/100)* Double.parseDouble(discountTxt.getText());
        if (Double.parseDouble(discountTxt.getText())<=100 && Double.parseDouble(discountTxt.getText())>0){
            paybleTotal = subTotal-discount;
            paybleTotalLbl.setText(String.valueOf(paybleTotal));
        }else{
            new Alert(Alert.AlertType.WARNING,"Please check again... ").show();
        }
    }

    @FXML
    void placeOrderBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "is this customer is correct ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        PlaceOrderModel.setNewOrder(orderIdLbl.getText(),customerIdCmb.getValue(),Double.parseDouble(paybleTotalLbl.getText()),Double.parseDouble(discountTxt.getText()));
        if (buttonType.get() == ButtonType.YES) {
            for (PlaceOrderTabelModel p :placeOrderTabelModelsList){
                try{
                    // Transaction
                    conn = DBConnection.getInstance().getConnection();
                    conn.setAutoCommit(false);
                    boolean flag = PlaceOrderModel.saveOrderItem(new OrderItems(orderIdLbl.getText(), p.getpId(), p.getQty(), p.getRowTotal()));

                    if (flag){
                        boolean b = ProductModel.updateQty(p.getpId(), p.getQty());
                        if (b){
                            conn.commit();
                        }
                    }else{
                        new Alert(Alert.AlertType.ERROR,"Something Wrong in process of save your order...");
                        conn.setAutoCommit(true);
                        conn.rollback();
                    }
                }catch (Exception e){
                     new Alert(Alert.AlertType.ERROR, ""+e).showAndWait();
                }finally {
                    conn.setAutoCommit(true);
                }

            }
        }else{
            return;
        }
        this.placeOrderTabelModelsList.clear();
        PlaceOrderTableTemplateModel.setPlaceOrderCart().clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setTableData();

        try {
            ArrayList<String> customerData = PlaceOrderModel.getCustomerData();
            ObservableList<String> obList = FXCollections.observableArrayList();
            setOrderId();
            for (String id : customerData) {
                obList.add(id);
            }

            customerIdCmb.setItems(obList);
            customerIdCmb.setValue(obList.get(0));
            setSubTotal();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        noCol.setCellValueFactory(new PropertyValueFactory<>("no"));
        productCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("rowTotal"));
        moreCol.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }

    private void setOrderId() throws SQLException, ClassNotFoundException {
        String lastOrderId = PlaceOrderModel.getLastOrderId();
        if (lastOrderId == null){
            lastOrderId="Ord00001";
        }
        String[] orderIdArr = lastOrderId.split("[d]");
        int lastNumber = Integer.parseInt(orderIdArr[1])+1;
        String newId = String.format("Ord%05d",lastNumber);
        orderIdLbl.setText(newId);
    }

    private void setSubTotal() throws SQLException, ClassNotFoundException {
        subTotal=0;
        for (PlaceOrderTabelModel potm:placeOrderTabelModelsList){
            subTotal+=potm.getRowTotal();
        }
        subTotalLbl.setText(String.valueOf(subTotal));
        paybleTotalLbl.setText(String.valueOf(subTotal));
    }

    private void setTableData() {
        productTbl.getItems().clear();
        this.placeOrderTabelModelsList = PlaceOrderTableTemplateModel.setPlaceOrderCart();
        /*removeDataFromTable();*/
        for (PlaceOrderTabelModel p : placeOrderTabelModelsList){
            p.btn.setOnAction(event -> {
                System.out.println("clicked btn");
                Alert alert = new Alert(Alert.AlertType.WARNING,"Are you sure ?",ButtonType.YES,ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get() == ButtonType.YES){
                    placeOrderTabelModelsList.remove(p);
                    System.out.println(placeOrderTabelModelsList.size());
                    System.out.println(PlaceOrderTableTemplateModel.setPlaceOrderCart().size());
                    try {
                        setSubTotal();
                        productTbl.refresh();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        productTbl.setItems(placeOrderTabelModelsList);
        productTbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

        });
    }

    public void customerIdCmbOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customrName = PlaceOrderModel.getCustomrName(customerIdCmb.getValue());
        customerNameLbl.setText(customrName);
    }

/*btn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.WARNING,"Are You Sure ?",
                        ButtonType.OK,ButtonType.NO);
                boolean isRemove = placeOrderCartList.remove(ci);
                if (isRemove){
                    new Alert(Alert.AlertType.INFORMATION,"Successfully Removed Product");
                }else{
                    new Alert(Alert.AlertType.ERROR,"Try again");
                }
            });*/
}
