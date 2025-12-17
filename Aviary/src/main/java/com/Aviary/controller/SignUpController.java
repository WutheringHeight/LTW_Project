package com.Aviary.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

import com.Aviary.service.UserService;

@WebServlet("/signup")
public class SignUpController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("Login&SignUp/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int id = UserService.validateUser(email,password);
        
        if (id != 0) {
            request.getSession().setAttribute("UserID", id);
            response.sendRedirect("HomePage/homePage.jsp");
        } else {
            request.setAttribute("error", "Email or password is incorrect. Please try again.");
            request.getRequestDispatcher("Login&SignUp/signup.jsp").forward(request, response);
        }
    }
}
