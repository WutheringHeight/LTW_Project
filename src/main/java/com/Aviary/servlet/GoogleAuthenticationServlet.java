package com.Aviary.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/google_auth")
public class GoogleAuthenticationServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //This url will be what google will contact us back with details about the user for authentication.
        //This url should be updated in the case our domain name changes
        String callBackURL = "http://localhost:8080/Aviary-1.0/google_auth_callback";

         String googleAuthUrl =
            "https://accounts.google.com/o/oauth2/v2/auth"
          + "?client_id=29908543455-4tlaiuqtlgmv0ed0f92petpt8sf29ium.apps.googleusercontent.com"
          + "&redirect_uri=" + URLEncoder.encode(callBackURL, "UTF-8")
          + "&response_type=code"
          + "&scope=openid email profile";

        resp.sendRedirect(googleAuthUrl);
        
    }
}
