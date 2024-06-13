package com.mth.controller.user;

import com.mth.dao.CommentRepository;
import com.mth.model.Comments;
import com.mth.model.Movie;
import com.mth.model.User;
import com.mth.service.MovieService;
import com.mth.service.UserService;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private MovieService movieService;

	@Autowired
	private CommentRepository commentRepository;

	@GetMapping("/user")
	public String user(HttpSession session, Model model) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		if (loggedUser == null) {
			return "redirect:/login";
		}
		model.addAttribute("user", loggedUser);
		return "user/user";
	}

	@PostMapping("/user/save")
	public String saveUserProfile(@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("avatar") String avatar, HttpSession session) {

		User loggedUser = (User) session.getAttribute("loggedUser");
		if (loggedUser == null) {
			return "redirect:/login";
		}

		userService.updateUserProfile(loggedUser, username, email, password, avatar);
		loggedUser.setUsername(username); 
		loggedUser.setEmail(email);
		loggedUser.setPassword(password);
		loggedUser.setAvatar(avatar);
		session.setAttribute("loggedUser", loggedUser);
		return "redirect:/user?success";
	}

	@PostMapping("/user/send-otp")
	@ResponseBody
	public Map<String, Object> sendOtp(@RequestParam("email") String email, HttpSession session) {
		Map<String, Object> response = new HashMap<>();
		if (userService.isEmailExists(email)) {
			String otp = userService.sendOtpToEmail(email, session);
			response.put("success", true);
			response.put("message", "OTP sent successfully");
		} else {
			response.put("success", false);
			response.put("message", "Email does not exist");
		}
		return response;
	}

	@PostMapping("/user/verify-otp")
	@ResponseBody
	public Map<String, Object> verifyOtp(@RequestParam("otp") String otp, HttpSession session) {
		Map<String, Object> response = new HashMap<>();
		boolean verified = userService.verifyOtp(otp, session);
		response.put("success", verified);
		response.put("message", verified ? "OTP verified successfully" : "Invalid OTP");
		return response;
	}

	@PostMapping("/user/reset-password")
	@ResponseBody
	public Map<String, Object> resetPassword(@RequestParam("email") String email,
			@RequestParam("newPassword") String newPassword) {
		Map<String, Object> response = new HashMap<>();
		try {
			userService.updatePasswordByEmail(email, newPassword);
			response.put("success", true);
			response.put("message", "Password updated successfully");
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", "Failed to update password");
		}
		return response;
	}

	@PostMapping("/{movieId}/comments")
	public String saveComment(@PathVariable Long movieId, @RequestParam("description") String description,
			@RequestParam("rating") int rating, HttpSession session) {

		User loggedUser = (User) session.getAttribute("loggedUser");
		if (loggedUser == null) {
			return "redirect:/login";
		}

		Optional<Movie> movieOptional = movieService.findById(movieId);
		if (movieOptional.isPresent()) {
			Movie movie = movieOptional.get();

			Comments comment = new Comments();
			comment.setMovie(movie);
			comment.setUser(loggedUser);
			comment.setDescription(description);
			comment.setRate(rating);
			commentRepository.save(comment);

			return "redirect:/" + movieId;
		} else {
			return "redirect:/error";
		}
	}
}