package com.yashen.wms.util;

import com.yashen.wms.to.Product;
import com.yashen.wms.to.ProductBoxModel;

import java.sql.SQLException;

public interface ProductBoxListener {
    public void onClickListener(Product product) throws SQLException, ClassNotFoundException;
}
