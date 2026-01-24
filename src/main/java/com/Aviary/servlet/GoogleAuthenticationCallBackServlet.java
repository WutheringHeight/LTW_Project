package com.Aviary.servlet;

import java.io.IOException;

import com.Aviary.dao.UserDao;
import com.Aviary.service.GoogleAuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/google_auth_callback")
public class GoogleAuthenticationCallBackServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String accessToken = GoogleAuthService.exchangeCodeForToken(code);
        if(accessToken == null) 
            System.err.println("Failed to get Google access token");

        String email = GoogleAuthService.getUserEmail(accessToken);
        HttpSession session = req.getSession();
        
        int userID = UserDao.getUserID(email);
        if(userID == -1){
            userID = UserDao.createNewGoogleUser(email);
        }
        session.setAttribute("userID", userID);

        resp.sendRedirect("/homepage");        
    }
    
}
