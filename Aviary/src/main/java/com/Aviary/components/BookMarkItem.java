package com.Aviary.components;

import java.io.Serializable;
import java.sql.Date;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class BookMarkItem implements Serializable{
     @ColumnName("ID")
    private int id;
     @ColumnName("accID")
    private int accountID;
     @ColumnName("PID")
    private int ProductID;
    private Date createdAt;
    
    public BookMarkItem(){}

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

}
