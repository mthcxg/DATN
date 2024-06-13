package com.mth.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mth.model.Movie;
import com.mth.service.MovieService;

@Controller
@RequestMapping("admin/film")
public class FilmController {
	@Autowired
	private MovieService movieService;

	@GetMapping
	public String getAllMovies(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(value = "search", required = false) String search) {
		Page<Movie> moviePage;
		if (search != null && !search.isEmpty()) {
			moviePage = movieService.searchMovies(search, page, size);
		} else {
			moviePage = movieService.findAllMovies(page, size);
		}
		model.addAttribute("moviePage", moviePage);
		model.addAttribute("newMovie", new Movie());
		model.addAttribute("editMovie", null); // Default to null
		return "admin/film/index";
	}

	@PostMapping("/add")
	public String addMovie(@ModelAttribute("newMovie") Movie movie) {
		movieService.saveMovie(movie);
		return "redirect:/admin/film";
	}

	@GetMapping("/edit/{id}")
	public String editMovie(@PathVariable("id") Integer id, Model model) {
		Movie movie = movieService.getReferenceById(id);
		model.addAttribute("editMovie", movie);
		return "admin/film/index";
	}

	@PostMapping("/update")
	public String updateMovie(@ModelAttribute("editMovie") Movie movie) {
		movieService.saveMovie(movie);
		return "redirect:/admin/film";
	}

	@PostMapping("/delete/{id}")
	public String deleteMovie(@PathVariable("id") Long id) {
		movieService.deleteMovieById(id);
		return "redirect:/admin/film";
	}
}