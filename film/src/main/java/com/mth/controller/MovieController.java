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
import com.mth.model.Category;
import com.mth.model.CommentInfo;
import com.mth.model.Movie;
import com.mth.service.ActorService;
import com.mth.service.CategoryService;
import com.mth.service.MovieService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller("/movies")
public class MovieController {
	@Autowired
	private MovieService movieService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ActorService actorService;

	@GetMapping("categories")
	public String index() {
		return "movies/categories";

	}

	@GetMapping("categories/{categoryId}/movies")
	public String index(@PathVariable(name = "categoryId") Integer categoryId,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "sortByRating", required = false, defaultValue = "false") boolean sortByRating,
			Model model, HttpServletResponse response, HttpSession session) throws IOException {
		if (categoryId == null) {
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
		Category category;
		int startIndex = (currentPage - 1) * pageSize;
		if (sortByRating) {
			movies = this.movieService.getMoviesByCategoryAndSortedByRating(categoryId);
		} else {
			movies = this.movieService.getMoviesByCategory(categoryId);
		}
		category = this.categoryService.getReferenceById(categoryId);
		int endIndex = Math.min(startIndex + pageSize, movies.size());
		List<Movie> moviesOnPage = movies.subList(startIndex, endIndex);
		model.addAttribute("movies", moviesOnPage);
		model.addAttribute("category", category);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", (int) Math.ceil((double) movies.size() / pageSize));
		return "movies/index";
	}

	@GetMapping("directors/{director}")
	public String index(@PathVariable(name = "director") String director,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "sortByRating", required = false, defaultValue = "false") boolean sortByRating,
			Model model, HttpServletResponse response, HttpSession session) throws IOException {
		if (director == null) {
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
		int startIndex = (currentPage - 1) * pageSize;
		if (sortByRating) {
			movies = this.movieService.getDirectorAndSortedByRating(director);
		} else {
			movies = this.movieService.getDirector(director);
		}
		int endIndex = Math.min(startIndex + pageSize, movies.size());
		List<Movie> moviesOnPage = movies.subList(startIndex, endIndex);
		model.addAttribute("movies", moviesOnPage);
		model.addAttribute("director", director);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", (int) Math.ceil((double) movies.size() / pageSize));
		return "movies/director";
	}

	@GetMapping("hot")
	public String hot(Model m) {
		List<Movie> hot = this.movieService.getHotMovie();
		m.addAttribute("hot", hot);
		return "movies/hot";
	}

	@GetMapping("/{movie_id}")
	public String show(@PathVariable(name = "movie_id") Integer id, Model m, HttpServletResponse response)
			throws IOException {
		Movie movie = this.movieService.getReferenceById(id);
		List<Category> categories = this.categoryService.getCategoriesForMovie(id);
		List<Actor> actors = this.actorService.getActorsForMovie(id);
		List<CommentInfo> comments = this.movieService.getCommentByMovieId(id);
		int commentCount = this.movieService.countComments(id);
		m.addAttribute("movie", movie);
		m.addAttribute("categories", categories);
		m.addAttribute("actors", actors);
		m.addAttribute("commentCount", commentCount);
		m.addAttribute("comments", comments);
		return "movies/show";
	}

	@GetMapping("/search")
	public String search(@RequestParam(value = "category", required = true) Integer categoryId,
			@RequestParam(value = "keyword", required = true) String keyword,
			@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {

		int pageSize = 32;
		Integer currentPage;
		if (page != null) {
			currentPage = page;
		} else {
			currentPage = 1;
		}
		List<Movie> movies = null;
		int startIndex = (currentPage - 1) * pageSize;
		String id = String.valueOf(categoryId);
		if (id.equals("-1")) {
			movies = this.movieService.searchAllCategories(keyword);
		} else {
			Category selectedCategory = this.categoryService.getReferenceById(categoryId);
			if (selectedCategory == null) {
				return "error";
			}
			movies = this.movieService.searchByCategory(categoryId, keyword);
		}

		int endIndex = Math.min(startIndex + pageSize, movies.size());
		List<Movie> moviesOnPage = movies.subList(startIndex, endIndex);
		model.addAttribute("movies", moviesOnPage);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", (int) Math.ceil((double) movies.size() / pageSize));
		return "movies/search";
	}

}
