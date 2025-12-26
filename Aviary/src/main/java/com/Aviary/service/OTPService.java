package com.Aviary.service;

import java.util.Random;

public class OTPService{
    public static String sendOTP(String email){
        Random rd = new Random();
        StringBuilder otpCode = new StringBuilder();

        for(int i=0;i< 6;i++)
            otpCode.append(rd.nextInt(10));

        System.out.println(otpCode.toString());

        String subject = "Your email has been used to sign up for Aviary.Co";
        String body = "Here is the OTP to confirm your account creation \n OTP:" + otpCode;
        EmailService.send(email, subject, body);

        return otpCode.toString();
    }
}