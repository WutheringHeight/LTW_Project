package com.Aviary.service;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailService {
    private static String from = "higjuly7@gmail.com";
    private static String appPassword = "lnla smnz zuho ephi";

    public static void send(String to, String subject,String body){
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        //Warning: this line of code(this config below) should not be in production code as it is a security hazard
        //This is only present so this email service will work regardless of local factors like fire walls and school wifi connection blocking
        //If this is ever to be used in production, this line of code should be removed and firewalls or internet connection to be set up right
        prop.put("mail.smtp.ssl.trust", "*");

        Authenticator auth = new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(from, appPassword);
            }
        };
        Session session = Session.getInstance(prop, auth);

        try{
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            msg.setSubject(subject);
            msg.setText(body);

            Transport.send(msg);
        }catch(MessagingException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to send email");
        }
    }
}
