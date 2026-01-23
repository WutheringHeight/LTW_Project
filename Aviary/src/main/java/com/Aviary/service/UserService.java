package com.Aviary.service;

import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.Aviary.components.User;
import com.Aviary.dao.UserDao;

public class UserService {
    
    public static int validateUser(String email,String password){
        if(BCrypt.checkpw(password, UserDao.getUserPassword(email)))
            return UserDao.getUserID(email);
        return -1;
    }
    public static boolean userExist(String email){
        return UserDao.getUserID(email) == -1;
    }
    public static int createNewAccount(String email,String password){
        try{
            UserDao.createNewUser(email, BCrypt.hashpw(password, BCrypt.gensalt()));
            return UserDao.getUserID(email);
        }catch(SQLException e){
            System.err.println("Failed to create new account");  
        }
        return -1;
    }
}
