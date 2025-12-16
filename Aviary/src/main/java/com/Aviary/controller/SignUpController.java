package com.Aviary.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

@WebServlet("/signup")
public class SignUpController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setAttribute("error", "nig");
         request.getRequestDispatcher("signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        if (validateInput(username, email, password)) {
            // TODO: Save user to database
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("error", "Invalid input");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }

    private boolean validateInput(String username, String email, String password) {
        return username != null && !username.isEmpty() &&
               email != null && email.contains("@") &&
               password != null && password.length() >= 6;
    }
}
