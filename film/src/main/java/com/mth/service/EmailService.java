package com.mth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public String sendOTP(String toEmail) {
        Random random = new Random();
        String otp = String.valueOf(100000 + random.nextInt(900000)); // Tạo số OTP 6 chữ số

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("movieholic1702@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otp);
        mailSender.send(message);
        System.out.println("Mail sent success");
        return otp;
    }
}