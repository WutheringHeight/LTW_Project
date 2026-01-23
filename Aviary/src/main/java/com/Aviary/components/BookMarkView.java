package com.Aviary.components;

import java.io.Serializable;
import java.sql.Date;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class BookMarkView implements Serializable{
     @ColumnName("ID")
    private int id;
     @ColumnName("accID")
    private int accountID;
     @ColumnName("PID")
    private int ProductID;
    private String thumbnailPath;
    private String name;
    private double price;
    private int stock;
    private Date createdAt;
    
    public BookMarkView(){}

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountID() {
        return this.accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getProductID() {
        return this.ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    public String getThumbnailPath() {
        return this.thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
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

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    

}
