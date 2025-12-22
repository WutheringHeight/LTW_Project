package com.Aviary.dao;

import com.Aviary.components.JDBIProvider;

public class BookMarkDao {
    public static void addBookMark(int accountID, int productID){
        JDBIProvider.get().useHandle(handle -> {
            handle.createUpdate("insert into Bookmark_Item (accID, PID) values(:accID, :PID);")
            .bind("accID", accountID)
            .bind("PID", productID)
            .execute();
        });
    }
    
}
