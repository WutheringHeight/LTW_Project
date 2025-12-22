package com.Aviary.dao;

import com.Aviary.components.JDBIProvider;

public class PaymentDao {
    public static void createPayment(int receiptID, String deliveryAddress,String paymentMethod, String bankCode,String cardNumber){
        JDBIProvider.get().useHandle(handle -> 
            handle.createUpdate("insert into Payment_Transaction(Receipt_ID, deliveryAddress, paymentMethod, bankCode, cardNumber) values(:receipt_ID, :deliveryAddress, :paymentMethod, :bankCode, :cardNumber);")
            .bind("receipt_ID", receiptID)
            .bind("deliveryAddress", deliveryAddress)
            .bind("paymentMethod", paymentMethod)
            .bind("bankCode", bankCode)
            .bind("cardNumber", cardNumber)
            .execute()
        );
    }
}
