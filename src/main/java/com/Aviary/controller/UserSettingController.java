package com.Aviary.controller;

import java.io.IOException;
import java.util.List;

import com.Aviary.components.Notification;
import com.Aviary.components.UserSetting;
import com.Aviary.dao.NotificationDao;
import com.Aviary.dao.UserSettingDao;
import com.Aviary.service.Util;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/userSettings")
public class UserSettingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userID = Util.getFromSession(session,"userID",Integer.class);
        List<Notification> notifs = NotificationDao.getNotification(userID);
        request.setAttribute("notifications", notifs);
        UserSetting setting = UserSettingDao.getUserSetting(userID);
        request.setAttribute("userSetting", setting);
        request.getRequestDispatcher("UserDetails/userdetails-payment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

    }
}
