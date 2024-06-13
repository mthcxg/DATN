package com.mth.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.mth.model.Actor;
import com.mth.model.Movie;
import com.mth.service.ActorService;
import com.mth.service.MovieService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ActorController {
	@Autowired
	private MovieService movieService;

	@Autowired
	private ActorService actorService;

	@GetMapping("/actors/{actorId}")
	public String actor(@PathVariable(name = "actorId") Integer actorId,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "sortByRating", required = false, defaultValue = "false") boolean sortByRating,
			Model model, HttpServletResponse response, HttpSession session) throws IOException {
		if (actorId == null) {
			response.sendRedirect("/404-page");
			String errorMessage = "Không tìm thấy phim";
			model.addAttribute("errorMessage", errorMessage);
			return "error";
		}
		int pageSize = 32;
		Integer currentPage;
		if (page != null) {
			currentPage = page;
		} else {
			currentPage = 1;
		}
		List<Movie> movies;
		Actor actor;
		int startIndex = (currentPage - 1) * pageSize;
		if (sortByRating) {
			movies = this.movieService.getMoviesByActorAndSortedByRating(actorId);
		} else {
			movies = this.movieService.getMoviesByActor(actorId);
		}
		actor = this.actorService.getReferenceById(actorId);
		int endIndex = Math.min(startIndex + pageSize, movies.size());
		List<Movie> moviesOnPage = movies.subList(startIndex, endIndex);
		model.addAttribute("movies", moviesOnPage);
		model.addAttribute("actor", actor);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", (int) Math.ceil((double) movies.size() / pageSize));
		return "movies/actor";
	}

}
