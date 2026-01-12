package com.Aviary.servlet;

import java.io.IOException;

import com.Aviary.components.UserSetting;
import com.Aviary.dao.UserSettingDao;
import com.Aviary.service.Util;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/userSetting_Update")
public class UserSettingUpdateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        //WARNING: this should not be called ._.
        request.getRequestDispatcher("/userDetails_Setting").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        int userID = Util.getFromSession(request.getSession(), "userID",Integer.class);
        UserSetting userSetting = UserSettingDao.getUserSetting(userID);
        boolean orderUpdateNotif = (request.getParameter("order-Update") != null);
        boolean promoNotif = (request.getParameter("promotion-Offer-Notif") != null);
        boolean sendEmail = (request.getParameter("send-Email") != null);
        boolean sendSms = (request.getParameter("send-SMS") != null);
        
        userSetting.setOrderUpdateNotif(orderUpdateNotif);
        userSetting.setPromoNotif(promoNotif);
        userSetting.setSendEmail(sendEmail);
        userSetting.setSendSms(sendSms);

        UserSettingDao.updateUserSetting(userSetting);
        request.getRequestDispatcher("/userDetails_Setting").forward(request, response);
    }
}
