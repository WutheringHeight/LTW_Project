package com.Aviary.dao;

import com.Aviary.components.JDBIProvider;
import com.Aviary.components.UserDetail;

public class UserDetailDao {
    public static UserDetail getUserDetail(int userID){
        return JDBIProvider.get().withHandle(handle ->
            handle.createQuery("select * from UserDetails where accID = :userID")
            .bind("userID", userID)
            .mapToBean( UserDetail.class)
            .one()
        );
    }
    public static int insertUserDetails(int userID) {
    return JDBIProvider.get().withHandle(handle ->
        handle.createUpdate("insert into UserDetails(accID) Values(:userID)")
            .executeAndReturnGeneratedKeys("detailID")
            .mapTo(Integer.class)
            .one()
    );
}
    public static void updateUserDetail(UserDetail userDetail){
        JDBIProvider.get().useHandle(handle ->
        handle.createUpdate("update UserDetail set userName = :userName phoneNumber= :phoneNumber profilePicture = :profilePicture twoFactorEnabled = :twoFactorEnabled isActive = :isActive where ID = :userDetailsID")
            .bind("userName", userDetail.getUserName())
            .bind("phoneNumber",userDetail.getPhoneNumber())
            .bind("profilePicture",userDetail.getProfilePicture())
            .bind("twoFactorEnabled",userDetail.getTwoFactorEnabled())
            .bind("userDetailsID", userDetail.getAccountID())
            .execute()
        );
    }
}
