package com.mth.controller.admin;

import com.mth.model.User;
import com.mth.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private MovieService movieService;

	@RequestMapping
	public String admin(HttpSession session, Model model) {
		User user = (User) session.getAttribute("loggedUser");

		if (user == null || !user.isAdmin()) {
			model.addAttribute("errorMessage", "You are not the admin");
			return "login";
		}
		model.addAttribute("categoryCounts", movieService.countMoviesByCategory());
		return "admin/index";
	}
}