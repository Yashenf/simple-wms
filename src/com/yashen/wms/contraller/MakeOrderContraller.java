package com.yashen.wms.contraller;

import com.jfoenix.controls.JFXComboBox;
import com.yashen.wms.model.MakeOrderModel;
import com.yashen.wms.to.Product;
import com.yashen.wms.to.ProductBoxModel;
import com.yashen.wms.util.Navigation;
import com.yashen.wms.util.ProductBoxListener;
import com.yashen.wms.util.Routes;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MakeOrderContraller implements Initializable {

    public Label lsitemNameLbl;
    public Label lsItemPriceLbl;
    public ImageView lsItemPic;
    public JFXComboBox qtyCmb;
    public JFXComboBox colorCmb;
    public ScrollPane itemScrollpane;
    public GridPane gride;
    public ArrayList<ProductBoxModel> itemList;
    public int row;
    public int col;
    public Label totalPriceLbl;
    public AnchorPane context;
    public Label qtyOnHandLbl;
    private ProductBoxListener productBoxListener;
    public Product product;
    int rowTotal = 0;


    public void addToCartBtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int qty = (int) qtyCmb.getValue();
        int qtyOnHand = MakeOrderModel.setQty(product.getId());
        if (qtyOnHand>=qty){

            rowTotal += (int) (product.getPrice() * qty);
            totalPriceLbl.setText(String.valueOf(rowTotal));

            MakeOrderModel.setToCart(product, qty);
        }else{
            new Alert(Alert.AlertType.WARNING,"Sorry... Qty is less than your requrement").show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeComboBox();
        /*try {
            this.pbmList = MakeOrderModel.getProducts("any");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        try {
            ArrayList<Product> pbmList = MakeOrderModel.getProducts("any");
            setProducts(pbmList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setProducts(ArrayList<Product> pbmList) {

        productBoxListener = new ProductBoxListener() {
            @Override
            public void onClickListener(Product product) throws SQLException, ClassNotFoundException {
                selectedProductView(product);

            }
        };
        row = 0;
        col = 0;
        try {
            for (Product p : pbmList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/yashen/wms/view/item/ItemForm.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                if (col == 3) {
                    col = 0;
                    row++;
                }

                //grid width
                gride.setMinWidth(Region.USE_COMPUTED_SIZE);
                gride.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gride.setMaxWidth(Region.USE_PREF_SIZE);

                //grid height
                gride.setMinHeight(Region.USE_COMPUTED_SIZE);
                gride.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gride.setMaxHeight(Region.USE_PREF_SIZE);

                gride.add(anchorPane, col++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
                ItemFormContraller itemFormContraller = fxmlLoader.getController();
                if (p.getItemType().equals("vga")){
                    itemFormContraller.itemImage.setImage(new Image("com/yashen/wms/assets/images/vga.png"));
                }else if(p.getItemType().equals("laptop")){
                    itemFormContraller.itemImage.setImage(new Image("com/yashen/wms/assets/images/laptop.png"));
                }else if(p.getItemType().equals("motherBoard")){
                    itemFormContraller.itemImage.setImage(new Image("com/yashen/wms/assets/images/motherBoard.png"));
                }else if(p.getItemType().equals("moniter")){
                    itemFormContraller.itemImage.setImage(new Image("com/yashen/wms/assets/images/moniter.png"));
                }else{

                }
                itemFormContraller.setData(p, productBoxListener);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void selectedProductView(Product product) throws SQLException, ClassNotFoundException {
        this.product = product;
        lsItemPriceLbl.setText(String.valueOf(product.getPrice()));
        lsitemNameLbl.setText(product.getName());
        qtyOnHandLbl.setText(String.valueOf(MakeOrderModel.setQty(product.getId())));
        if (product.getItemType().equals("vga")) {
            lsItemPic.setImage(new Image("com/yashen/wms/assets/images/vga.png"));
        } else if (product.getItemType().equals("laptop")) {
            lsItemPic.setImage(new Image("com/yashen/wms/assets/images/laptop.png"));
        } else if (product.getItemType().equals("motherBoard")) {
            lsItemPic.setImage(new Image("com/yashen/wms/assets/images/motherBoard.png"));
        } else if (product.getItemType().equals("moniter")) {
            lsItemPic.setImage(new Image("com/yashen/wms/assets/images/moniter.png"));
        } else {

        }
    }

    public void initializeComboBox() {
        qtyCmb.getItems().addAll(50, 100, 200, 500, 1000, 2000, 5000);
        colorCmb.getItems().addAll("Black", "White");
    }


    public void viewCartOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PLACE_ORDER, context);
    }

    public void showVgaOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        setProducts(MakeOrderModel.getProducts("vga"));

    }

    public void showLapOnAction(MouseEvent mouseEvent) {
    }

    public void showMbOnAction(MouseEvent mouseEvent) {
    }

    public void showMonitersOnAction(MouseEvent mouseEvent) {
    }

}
