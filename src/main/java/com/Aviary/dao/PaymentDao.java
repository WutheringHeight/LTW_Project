package com.Aviary.dao;

import com.Aviary.components.JDBIProvider;
import com.Aviary.components.PaymentInfo;

public class PaymentDao {
    public static void createPaymentInfo(int receiptID, String deliveryAddress,String paymentMethod, String bankCode,String cardNumber){
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
    public static PaymentInfo getPaymentInfo(int userID){
         return JDBIProvider.get().withHandle(handle ->
            handle.createQuery(
                "select * from PaymentInfo where acc_ID = :accID")
            .bind("accID", userID)
            .mapToBean(PaymentInfo.class)
            .findOne()
            .orElse(null)
    );
    }
    public static void updatePaymentInfo(PaymentInfo paymentInfo){
        JDBIProvider.get().withHandle(handle ->
             handle.createUpdate("update PaymentInfo set deliveryAddress = :deliveryAddress, paymentMethod = :paymentMethod, bankCode = :bankCode, cardNumber = :cardNumber where acc_ID = :accountID")
            .bindBean(paymentInfo)
            .execute()
        );
    }

}
