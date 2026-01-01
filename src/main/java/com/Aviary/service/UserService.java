package com.Aviary.service;

import com.Aviary.components.User;
import com.Aviary.dao.UserDao;

public class UserService {
    
    public static int validateUser(String email,String password){
        return UserDao.getUserID(email, password);
    }
}
