package com.Aviary.components;

import java.io.Serializable;
import java.sql.Date;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class ChatMessage implements Serializable{
    @ColumnName("ID")
    private int id;
    @ColumnName("senderaccID")
    private int senderID;
    @ColumnName("receiveraccID")
    private int receiverID;
    private String message;
    private Date sentAt;
    @ColumnName("isread")
    private boolean isRead;

    public ChatMessage(){}

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderID() {
        return this.senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return this.receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSentAt() {
        return this.sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
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

}
