package com.mth.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mth.model.User;
import com.mth.service.UserService;


@Controller
@RequestMapping("admin/user")
public class UsersController {
	@Autowired
	private UserService userService;

	@GetMapping
	public String getUserList(Model model) {
		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);

		return "admin/user/index";
	}

}
