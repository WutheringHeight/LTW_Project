package com.Aviary.service;

import org.mindrot.jbcrypt.BCrypt;

import com.Aviary.components.User;
import com.Aviary.components.UserSetting;
import com.Aviary.dao.UserDao;
import com.Aviary.dao.UserDetailDao;
import com.Aviary.dao.UserSettingDao;

public class UserService {
    
    public static int validateUser(String email,String password, boolean needHash){
        String dbPassword = UserDao.getUserPassword(email);
        if(dbPassword == null) 
            return -1;
        if(needHash)
           password = BCrypt.hashpw(password, BCrypt.gensalt(10));
        if(!password.equals(dbPassword))
            return -1;
        return UserDao.getUserID(email);
    }

    public static int createNewAccount(String email, String password,boolean needHash) {
        if(needHash)
            password = BCrypt.hashpw(password, BCrypt.gensalt(10));
        int userID = UserDao.insertUser(email, password);
        UserSettingDao.insertUserSetting(userID);
        UserDetailDao.insertUserDetails(userID);
        return userID;
    }

    public static int createNewGoogleAccount(String email) {
        int userID = UserDao.insertGoogleUser(email);
        UserSettingDao.insertUserSetting(userID);
        UserDetailDao.insertUserDetails(userID);
        return userID;
    }

    
}
