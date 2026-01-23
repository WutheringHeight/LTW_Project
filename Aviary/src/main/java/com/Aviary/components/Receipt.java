package com.Aviary.components;

import java.io.Serializable;
import java.sql.Date;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Receipt implements Serializable{
     @ColumnName("ID")
    private int id;
    private int buyerID;
     @ColumnName("PID")
    private int ProductID;
    private int quantity;
    private double totalPrice;
    private Date createdAt;
    
    public Receipt(){
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuyerID() {
        return this.buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    public int getProductID() {
        return this.ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
}
