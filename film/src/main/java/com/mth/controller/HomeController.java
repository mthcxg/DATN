package com.mth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mth.model.Movie;
import com.mth.service.MovieService;

@Controller
public class HomeController {
	@Autowired
	private MovieService movieService;

	@RequestMapping("/home")
	public String home(Model m) {
		List<Movie> random = this.movieService.getRandomMovies();
		List<Movie> hot = this.movieService.getHotestMovies();
		m.addAttribute("random", random);
		m.addAttribute("hot", hot);
		return "user/home";
	}
}
