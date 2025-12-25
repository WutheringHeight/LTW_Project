package com.Aviary.service;

import java.util.Random;

public class OTPService{
    public static String sendOTP(String email){
        Random rd = new Random();
        StringBuilder otpCode = new StringBuilder();

        for(int i=0;i< 6;i++)
            otpCode.append(rd.nextInt(10));

        System.out.println(otpCode.toString());

        //TODO: send email before returning otpCode
        return otpCode.toString();
    }

    public static void main(String[] args) {
        System.err.println(sendOTP(""));
    }
}