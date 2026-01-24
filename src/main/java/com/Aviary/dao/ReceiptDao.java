package com.Aviary.dao;

import com.Aviary.components.JDBIProvider;

public class ReceiptDao {
    public static int createReceipt(int accountID){
        return JDBIProvider.get().withHandle(handle -> {
            return handle.createUpdate("insert into Receipt(buyerID) values(:accID);")
                .bind("accID", accountID)
                .executeAndReturnGeneratedKeys("ID")
                .mapTo(Integer.class)
                .one();
        });   
    } 
    public static void createReceiptItem(int receiptID,int productID, int quantity, double price){
          JDBIProvider.get().useHandle(handle -> 
            handle.createUpdate("insert into ReceiptItem(receiptID,PID, quantity, price) values(:receiptID, :PID, :quantity, :price);")
                .bind("receiptID", receiptID)
                .bind("PID",productID)
                .bind("quantity", quantity)
                .bind("price",price)
                .execute()
        );
    }
}
