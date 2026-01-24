package com.Aviary.servlet;

import java.io.IOException;

import com.Aviary.components.PaymentInfo;
import com.Aviary.components.UserDetail;
import com.Aviary.dao.PaymentDao;
import com.Aviary.dao.UserDetailDao;
import com.Aviary.service.Util;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/paymentInfo_Update")
public class UserPaymentUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        //WARNING: this should not be called
        request.getRequestDispatcher("/userDetails_Payment").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        int userID = Util.getFromSession(request.getSession(), "userID",Integer.class);
        PaymentInfo paymentInfo = PaymentDao.getPaymentInfo(userID);
        String address = Util.getString(request,"deliveryAddress");
        String bankCode = Util.getString(request, "bankCode");
        String cardNumber = Util.getString(request, "cardNumber");

        if(address != null) paymentInfo.setDeliveryAddress(address);
        if(bankCode != null) paymentInfo.setBankCode(bankCode);
        if(cardNumber != null) paymentInfo.setCardNumber(cardNumber);

        PaymentDao.updatePaymentInfo(paymentInfo);
       
    }
}
