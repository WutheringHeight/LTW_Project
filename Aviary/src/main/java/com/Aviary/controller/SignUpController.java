package com.Aviary.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import com.Aviary.dao.UserDao;
import com.Aviary.service.OTPService;
import com.Aviary.service.UserService;

@WebServlet("/signup")
public class SignUpController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("LoginNSignUp/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //email already used
        if(UserDao.getUserID(email) != -1){
            request.setAttribute("error", "Email already in-used");
            request.getRequestDispatcher("LoginNSignUp/signup.jsp").forward(request, response);
        }
        //continue with sign in process
        //forward to OTP
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt());
        session.setAttribute("hashedPassword", hashedPass);
        response.sendRedirect("signup_otp");

        
    }
}
