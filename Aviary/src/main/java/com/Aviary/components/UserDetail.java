package com.Aviary.components;

import java.io.Serializable;
import java.sql.Date;

public class UserDetail implements Serializable{
    private int accountID;              
    private String userName;
    private String phoneNumber;
    private String profilePicture;
    private boolean twoFactorEnabled;        
    private Date createdAt;

    public UserDetail(){}



    public int getAccountID() {
        return this.accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfilePicture() {
        return this.profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public boolean isTwoFactorEnabled() {
        return this.twoFactorEnabled;
    }

    public boolean getTwoFactorEnabled() {
        return this.twoFactorEnabled;
    }

    public void setTwoFactorEnabled(boolean twoFactorEnabled) {
        this.twoFactorEnabled = twoFactorEnabled;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
}
