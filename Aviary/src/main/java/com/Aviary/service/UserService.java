package com.Aviary.service;

import com.Aviary.components.User;
import com.Aviary.dao.UserDao;

public class UserService {
    
    public static int validateUser(String email,String password){
        if(password.equals(UserDao.getUserPassword(email)))
            return UserDao.getUserID(email);
        return -1;
    }
}
