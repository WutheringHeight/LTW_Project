package com.Aviary.controller;

import java.io.IOException;
import java.util.List;

import com.Aviary.components.Notification;
import com.Aviary.components.PaymentInfo;
import com.Aviary.components.UserDetail;
import com.Aviary.dao.NotificationDao;
import com.Aviary.dao.PaymentDao;
import com.Aviary.dao.UserDetailDao;
import com.Aviary.service.Util;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/userDetails_FAQ")
public class UserDetailsFAQController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("UserDetails/userdetails-faq.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("UserDetails/userdetails-faq.jsp").forward(request, response);
    }
}
