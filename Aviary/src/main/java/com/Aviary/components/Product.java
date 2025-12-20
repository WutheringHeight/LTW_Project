package com.Aviary.components;

import java.io.Serializable;
import java.sql.Date;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Product implements Serializable{
    @ColumnName("ID")
    private int id;
     @ColumnName("productName")
    private String name;
    private double price;
     @ColumnName("thumbnail")
    private String thumbnailPath;
    private int soldQuantity;
    private String description,category;
    private int stock;
    private Date createdAt;

    public Product(){}


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getThumbnailPath() {
        return this.thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public int getSoldQuantity() {
        return this.soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
