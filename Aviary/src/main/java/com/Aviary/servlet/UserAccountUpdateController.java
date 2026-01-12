package com.Aviary.servlet;

import java.io.IOException;

import com.Aviary.components.UserDetail;
import com.Aviary.dao.UserDetailDao;
import com.Aviary.service.Util;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/userAccount_Update")
public class UserAccountUpdateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        //WARNING: this should not be called
        request.getRequestDispatcher("/userDetails_Account").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        int userID = Util.getFromSession(request.getSession(), "userID",Integer.class);
        UserDetail userDetail = UserDetailDao.getUserDetail(userID);
        String username = request.getParameter("username");
        String PhoneNumber = request.getParameter("phoneNumber");
        boolean TFA = (request.getParameter("TFA") != null);

        userDetail.setUserName(username);
        userDetail.setPhoneNumber(PhoneNumber);
        userDetail.setTwoFactorEnabled(TFA);

        UserDetailDao.updateUserDetail(userDetail);
        request.getRequestDispatcher("/userDetails_Account").forward(request, response);
    }
}
