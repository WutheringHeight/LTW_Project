package com.Aviary.components;

import java.time.LocalDateTime;
import java.util.List;

public class Product {
    private int id;
    private String productName;
    private double price;
    private double rating;
    private int soldQuantity;
    private String thumbnail;
    private String description;
    private int category_id;
    private String kind;
    private int stock;
    private List<ProductImage> images;
    private LocalDateTime createdAt;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public double getRating() {return this.rating;}
    public void setRating(double rating) {this.rating = rating;}

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category) {
        this.category_id = category_id;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    public LocalDateTime  getCreatedAt() {return createdAt;}
    public void setCreatedAt(LocalDateTime  createdAt) {this.createdAt = createdAt;}
}
