package com.Aviary.service;

import com.Aviary.components.User;
import com.Aviary.dao.UserDao;

public class UserService {
    
    public static int validateUser(String email,String password){
        return UserDao.getUserID(email);

    }

    public static int createNewAccount(String email, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createNewAccount'");
    }
}
