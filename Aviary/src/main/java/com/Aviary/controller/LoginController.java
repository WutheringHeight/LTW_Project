package com.Aviary.controller;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import com.Aviary.dao.UserDao;
import com.Aviary.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
            //valid log in info
            boolean twoFA = UserDao.getUserDetail(id).getTwoFactorEnabled();
            if(twoFA){
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                response.sendRedirect("login_otp");
            }else{
                request.getSession().setAttribute("UserID", id);
                response.sendRedirect("HomePage/homePage.jsp");
            }
        } else {
            //not right
            request.setAttribute("error", "Email or password is incorrect. Please try again.");
            request.getRequestDispatcher("LoginNSignUp/login.jsp").forward(request, response);
        }
    }
}
