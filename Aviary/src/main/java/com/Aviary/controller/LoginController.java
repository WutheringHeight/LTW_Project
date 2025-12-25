package com.Aviary.controller;

import java.io.IOException;

import com.Aviary.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("LoginNSignUp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int id = UserService.validateUser(email,password);
        
        if (id != -1) {
            request.getSession().setAttribute("UserID", id);
            response.sendRedirect("HomePage/homePage.jsp");
        } else {
            request.setAttribute("error", "Email or password is incorrect. Please try again.");
            request.getRequestDispatcher("LoginNSignUp/login.jsp").forward(request, response);
        }
    }
}
