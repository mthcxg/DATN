package com.mth.controller.user;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.mth.model.User;
import com.mth.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("user", new User());
		return mav;
	}

	@PostMapping("/register")
	@ResponseBody
	public Map<String, Object> register(@RequestParam String username, @RequestParam String email,
			@RequestParam String password, HttpSession session) {
		Map<String, Object> response = new HashMap<>();

		if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
			response.put("success", false);
			response.put("message", "All fields are required!");
			return response;
		}

		if (userService.isEmailExists(email)) {
			response.put("success", false);
			response.put("message", "Email already in use.");
			return response;
		}

		// Gửi OTP và lưu thông tin đăng ký vào session
		userService.sendOtpToEmail(email, session);
		session.setAttribute("pendingUsername", username);
		session.setAttribute("pendingEmail", email);
		session.setAttribute("pendingPassword", password);

		response.put("success", true);
		return response;
	}

	@PostMapping("/verify-otp")
	@ResponseBody
	public Map<String, Object> verifyOtp(@RequestParam String otp, HttpSession session) {
		Map<String, Object> response = new HashMap<>();

		// Xác thực OTP
		boolean otpValid = userService.verifyOtp(otp, session);
		if (!otpValid) {
			response.put("success", false);
			response.put("message", "Invalid OTP.");
			return response;
		}

		// Lấy thông tin người dùng từ session
		String username = (String) session.getAttribute("pendingUsername");
		String email = (String) session.getAttribute("pendingEmail");
		String password = (String) session.getAttribute("pendingPassword");
		String avatar = "https://thumb.ac-illust.com/86/860c4500ad8a5c70695185f4a8988dfe_w.jpeg";
		if (username == null || email == null || password == null) {
			response.put("success", false);
			response.put("message", "Failed to retrieve user information.");
			return response;
		}

		User user = new User(username, email, password, avatar);
		userService.saveUser(user);

		// Xóa thông tin đăng ký khỏi session
		session.removeAttribute("pendingUsername");
		session.removeAttribute("pendingEmail");
		session.removeAttribute("pendingPassword");

		// Lưu thông tin người dùng vào session
		session.setAttribute("loggedInUser", user);

		response.put("success", true);
		response.put("message", "OTP verified and user logged in successfully.");
		return response;
	}
}