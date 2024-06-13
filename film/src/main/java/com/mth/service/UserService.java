package com.mth.service;

import com.mth.dao.UserRepository;
import com.mth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public void updateUserProfile(User loggedUser, String username, String email, String password, String avatar) {
        if (username != null && !username.isEmpty()) {
            loggedUser.setUsername(username);
        }
        if (email != null && !email.isEmpty()) {
            loggedUser.setEmail(email);
        }
        if (password != null && !password.isEmpty()) {
            loggedUser.setPassword(password);
        }
        if (avatar != null && !avatar.isEmpty()) {
            loggedUser.setAvatar(avatar);
        }

        saveUser(loggedUser);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
    

    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public String sendOtpToEmail(String email, HttpSession session) {
        String otp = emailService.sendOTP(email);
        session.setAttribute("otp", otp); // Lưu OTP vào session
        return otp;
    }

    public boolean verifyOtp(String inputOtp, HttpSession session) {
        String otp = (String) session.getAttribute("otp");
        return otp != null && otp.equals(inputOtp);
    }
    
    public void updatePasswordByEmail(String email, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setPassword(newPassword); 
            saveUser(user); 
        }
    }
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}