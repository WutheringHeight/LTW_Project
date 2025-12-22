package com.Aviary.dao;

import com.Aviary.components.JDBIProvider;

public class ReceiptDao {
    public static void createReceipt(int accountID, int productID, int quantity, double price){
        JDBIProvider.get().useHandle(handle -> 
            handle.createUpdate("insert into Receipt(buyerID, product, quantity, totalPrice) values(:accID, :PID, :quantity, :price * :quantity);")
                .bind("accID",accountID)
                .bind("PID", productID)
                .bind("quantity",quantity)
                .bind("price", price)
                .execute()
        );
    } 
}
