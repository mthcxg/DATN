package com.mth.controller.user;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mth.model.User;
import com.mth.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView m = new ModelAndView("login");
		m.addObject("user", new User());
		return m;
	}

	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute("user") User user, HttpSession session) {
		User oauthUser = userService.login(user.getEmail(), user.getPassword());
		if (Objects.nonNull(oauthUser)) {
			session.setAttribute("loggedUser", oauthUser);
			session.setAttribute("avatar", oauthUser.getAvatar());

			if (oauthUser.isAdmin()) {
				return new ModelAndView("redirect:/admin");
			} else {
				return new ModelAndView("redirect:/home");
			}
		} else {
			ModelAndView mav = new ModelAndView("login");
			mav.addObject("errorMessage", "Wrong email or password!");
			return mav;
		}
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.POST)
	public String logoutDo(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

}