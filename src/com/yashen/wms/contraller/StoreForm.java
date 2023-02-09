package com.yashen.wms.contraller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.yashen.wms.model.StoreModel;
import com.yashen.wms.to.Store;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StoreForm implements Initializable {

    public JFXTextField storeIdSaveTxt;
    public JFXTextField storeLocationSaveTxt;
    @FXML
    private TableView<Store> storeTbl;

    @FXML
    private TableColumn<?, ?> noCol;

    @FXML
    private TableColumn<?, ?> storeIdCol;

    @FXML
    private TableColumn<?, ?> locationCol;

    @FXML
    private JFXComboBox<String> storeIdCmb;

    @FXML
    private JFXTextField storeLocationUpdateTxt;

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (!storeIdCmb.getValue().isEmpty()){
            boolean b = StoreModel.deleteData(storeIdCmb.getValue());
            if (b){
                new Alert(Alert.AlertType.INFORMATION,"Successfully deleted Store", ButtonType.OK).showAndWait();
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Try again ", ButtonType.OK).showAndWait();
            }
        }
        clearUpdateFields();
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (!storeIdSaveTxt.getText().isEmpty() && !storeLocationSaveTxt.getText().isEmpty()){
            boolean b = StoreModel.saveStore(new Store(0, storeIdSaveTxt.getText(), storeLocationSaveTxt.getText()));
            if (b){
                new Alert(Alert.AlertType.INFORMATION,"Successfully deleted Store", ButtonType.OK).showAndWait();
            }else{
                new Alert(Alert.AlertType.INFORMATION,"Try again ", ButtonType.OK).showAndWait();
            }
        }
        clearSaveFields();
    }

    @FXML
    void storeIdCmbOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        Store selectedStore = StoreModel.getSelectedStore(storeIdCmb.getValue());
        if (selectedStore != null){
            storeLocationUpdateTxt.setText(selectedStore.getLocation());
        }

    }


    @FXML
    void updateBtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (!storeIdCmb.getValue().isEmpty() && !storeLocationUpdateTxt.getText().isEmpty()) {
            System.out.println("call with "+storeLocationUpdateTxt.getText().trim()+" , "+ storeIdCmb.getValue());
            boolean b = StoreModel.updateStore(storeLocationUpdateTxt.getText().trim(), storeIdCmb.getValue());
            System.out.println(b);
            if (b){
                new Alert(Alert.AlertType.INFORMATION,"Successfully updated Store", ButtonType.OK).showAndWait();
            }else{
                new Alert(Alert.AlertType.INFORMATION,"Try again ", ButtonType.OK).showAndWait();
            }
        }
        clearUpdateFields();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            noCol.setCellValueFactory(new PropertyValueFactory<>("no"));
            storeIdCol.setCellValueFactory(new PropertyValueFactory<>("storeId"));
            locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            loadDataForTable();
            initializeCmb();
            setNextStoreId();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setNextStoreId() throws SQLException, ClassNotFoundException {
        String sId = StoreModel.getNextStoreId();
        String[] sIdArr = sId.split("[t]");
        int lastDigit = Integer.parseInt(sIdArr[1])+1;
        String newId = String.format("st%03d",lastDigit);
        storeIdSaveTxt.setText(newId);
    }

    private void loadDataForTable() throws SQLException, ClassNotFoundException {
        ArrayList<Store> allStores = StoreModel.getAllStores();
        ObservableList<Store> allStoresObList = FXCollections.observableArrayList();
        if (allStores != null){
            allStoresObList.addAll(allStores);
        }else{
            new Alert(Alert.AlertType.INFORMATION,"Something Error 1 ", ButtonType.OK).showAndWait();
            return;
        }

        storeTbl.setItems(allStoresObList);
        /*storeTbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

                storeLocationUpdateTxt.setText(newValue.getLocation());
                storeIdCmb.setValue(newValue.getStoreId());


        });*/
        storeTbl.refresh();
    }

    private void initializeCmb() throws SQLException, ClassNotFoundException {
        if (!storeIdCmb.getItems().isEmpty()){
            storeIdCmb.getItems().clear();
        }
        ArrayList<Store> allStores = StoreModel.getAllStores();
        for (Store s : allStores){
            storeIdCmb.getItems().add(s.getStoreId());
        }
        storeIdCmb.setValue(allStores.get(0).getStoreId());
    }

    private void clearUpdateFields() throws SQLException, ClassNotFoundException {
        storeLocationUpdateTxt.clear();
        initializeCmb();
        loadDataForTable();
    }
    private void clearSaveFields() throws SQLException, ClassNotFoundException {
        storeLocationSaveTxt.clear();
        setNextStoreId();
    }
}
