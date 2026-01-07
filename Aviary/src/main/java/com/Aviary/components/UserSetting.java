package com.Aviary.components;

import java.sql.Date;

public class UserSetting {
    private int ID;                    
    private int accountId;               
    private boolean orderUpdateNotif;   
    private boolean promoNotif;         
    private boolean sendEmail;          
    private boolean sendSms;             
    private String language;                     
    private Date updatedAt;
    
    public UserSetting(){}


    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public boolean isOrderUpdateNotif() {
        return this.orderUpdateNotif;
    }

    public boolean getOrderUpdateNotif() {
        return this.orderUpdateNotif;
    }

    public void setOrderUpdateNotif(boolean orderUpdateNotif) {
        this.orderUpdateNotif = orderUpdateNotif;
    }

    public boolean isPromoNotif() {
        return this.promoNotif;
    }

    public boolean getPromoNotif() {
        return this.promoNotif;
    }

    public void setPromoNotif(boolean promoNotif) {
        this.promoNotif = promoNotif;
    }

    public boolean isSendEmail() {
        return this.sendEmail;
    }

    public boolean getSendEmail() {
        return this.sendEmail;
    }

    public void setSendEmail(boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public boolean isSendSms() {
        return this.sendSms;
    }

    public boolean getSendSms() {
        return this.sendSms;
    }

    public void setSendSms(boolean sendSms) {
        this.sendSms = sendSms;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
