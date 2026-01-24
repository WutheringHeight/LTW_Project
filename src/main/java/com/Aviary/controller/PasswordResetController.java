package com.Aviary.controller;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import com.Aviary.dao.UserDao;
import com.Aviary.service.Util;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/password_reset")
public class PasswordResetController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("LoginNSignUp/passwordReset.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        HttpSession session = request.getSession();
        String email = Util.getFromSession(session, "email", String.class);


        if(password.equals(password2)){
            String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt());
            int userID = UserDao.getUserID(email);
            UserDao.updateUserPassword(userID, hashedPass);
            session.setAttribute("UserID", userID);
            response.sendRedirect("homepage");
        }else{
            //password not right
            request.setAttribute("error", "The new passwords must be identical. Please try typing again");
            request.getRequestDispatcher("LoginNSignUp/passwordReset.jsp").forward(request, response);
        }
        
    }
}
