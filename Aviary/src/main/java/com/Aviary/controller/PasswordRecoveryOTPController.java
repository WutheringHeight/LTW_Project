package com.Aviary.controller;

import java.io.IOException;

import com.Aviary.dao.UserDao;
import com.Aviary.service.OTPService;
import com.Aviary.service.UserService;
import com.Aviary.service.Util;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/password_recovery_otp")
public class PasswordRecoveryOTPController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = Util.getFromSession(session, "email", String.class);
        String otp = OTPService.sendOTP(email);
        session.setAttribute("otp", otp);
        req.setAttribute("otpCallBack", "password_recovery_otp");
        req.getRequestDispatcher("LoginNSignUp/OTP.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String otp = Util.getFromSession(session, "otp", String.class);
        String input = OTPService.getOTP(req);

        if(otp.equals(input)){
            //proceeds with password reset
            resp.sendRedirect("passwordReset");
        }
        //wrong otp
        req.setAttribute("error", "The OTP you've just typed is incorrect.");
        req.setAttribute("otpCallBack", "password_recovery_otp");
        req.getRequestDispatcher("LoginNSignUp/OTP.jsp").forward(req, resp);
        
    }
}
