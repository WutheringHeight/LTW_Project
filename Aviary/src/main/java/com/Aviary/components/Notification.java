package com.Aviary.components;

import java.sql.Date;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Notification {
     @ColumnName("ID")
    private int id;
     @ColumnName("accID")
    private int accountID;
    private String message;
     @ColumnName("isread")
    private boolean isRead;
    private Date createdAt;

    public Notification(){}

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

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isIsRead() {
        return this.isRead;
    }

    public boolean getIsRead() {
        return this.isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
