package com.Aviary.ViewModel;

import com.Aviary.components.Product;

public class ProductSuggest {
    private int id;
    private String productName;
    private double price;
    private String thumbnail;

    public ProductSuggest(Product p) {
        this.id = p.getId();
        this.productName = p.getProductName();
        this.price = p.getPrice();
        this.thumbnail = p.getThumbnail();
    }
}
