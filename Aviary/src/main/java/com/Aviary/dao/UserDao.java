package com.Aviary.dao;

import java.sql.SQLException;

import com.Aviary.components.JDBIProvider;

public class UserDao {
    
    public static int getUserID(String email){
        return JDBIProvider.get().withHandle(handle -> {
            return handle.createQuery("Select ID from UserAccount where email = :email")
            .bind("email", email)
            .mapTo(Integer.class)
            .one();
        });
    }
    public static String getUserPassword(String email){
        return JDBIProvider.get().withHandle(handle -> {
            String password = handle.createQuery(
            "SELECT password FROM UserAccount WHERE email=:email")
            .bind("email", email)
            .mapTo(String.class).findOne().orElse(null);
            return password;
        });
    }
    public static void updateUserPassword(int ID, String newPassword){
        JDBIProvider.get().useHandle(handle -> {
            handle.createUpdate("update UserAccount set password = :newPassword where accID = :accID")
            .bind("newPassword", newPassword)
            .bind("accID",ID);
        });
    }
    public static void createNewUser(String email,String password) throws SQLException{
        JDBIProvider.get().useHandle(handle -> {
            handle.createUpdate("EXEC RegisterUser @Email = :email @Password = :password")
            .bind("email", email)
            .bind("password", password);
        });
    }

    

}
