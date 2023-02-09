package com.yashen.wms.contraller;

import com.yashen.wms.model.MakeOrderModel;
import com.yashen.wms.to.Product;
import com.yashen.wms.to.ProductBoxModel;
import com.yashen.wms.util.ProductBoxListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class ItemFormContraller {
    public ImageView itemImage;
    public Label itemNameLbl;
    public Label itemPriceLbl;
    public Label proIdLbl;
    private Product product;
    private ProductBoxListener productBoxListener;

    public void setData(Product product, ProductBoxListener productBoxListener) {
        this.product = product;
        this.productBoxListener = productBoxListener;
        itemNameLbl.setText(product.getName());
        itemPriceLbl.setText(String.valueOf(product.getPrice()));
        proIdLbl.setText(product.getId());

        /*if (product.getItemType().equals("vga")) {
            itemImage.setImage(new Image("com/yashen/wms/assets/images/vga.png"));
        } else if (product.getItemType().equals("laptop")) {
            itemImage.setImage(new Image("com/yashen/wms/assets/images/laptop.png"));
        } else if (product.getItemType().equals("motherBoard")) {
            itemImage.setImage(new Image("com/yashen/wms/assets/images/motherBoard.png"));
        } else if (product.getItemType().equals("moniter")) {
            itemImage.setImage(new Image("com/yashen/wms/assets/images/moniter.png"));
        } else {

        }*/
    }

    public void itemOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException, IOException {
        productBoxListener.onClickListener(product);
    }

    /*Product p = MakeOrderModel.getProductDataToMainContext(proIdLbl.getText());
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/yashen/wms/view/orders/MakeOrder.fxml"));
        fxmlLoader.load();
    MakeOrderContraller makeOrderContraller = fxmlLoader.getController();
        makeOrderContraller.lsitemNameLbl.setText(p.getName());
        makeOrderContraller.lsItemPriceLbl.setText(String.valueOf(p.getPrice()));*/
}
