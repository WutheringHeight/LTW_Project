package com.Aviary.service;

import com.Aviary.dao.ProductImageDAO;
import com.Aviary.components.ProductImage;

public class ProductImageService {
    private ProductImageDAO productImageDAO = new ProductImageDAO();

    public ProductImageService() {}

    public void addProductImage(ProductImage pi){
        productImageDAO.addProductImage(pi);
    }
}
