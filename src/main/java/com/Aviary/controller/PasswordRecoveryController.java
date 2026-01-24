package com.Aviary.controller;

import java.io.IOException;

import com.Aviary.dao.UserDao;
import com.Aviary.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/password_recovery")
public class PasswordRecoveryController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("LoginNSignUp/passwordRecovery.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        if(email.isEmpty()){
            request.setAttribute("error", "Email can't be empty. Please type your email");
            request.getRequestDispatcher("LoginNSignUp/passwordRecovery.jsp").forward(request, response);
        }
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        response.sendRedirect("/password_recovery_otp");

    }
}
