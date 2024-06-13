package com.mth.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.mth.dao.FavoritesRepository;
import com.mth.dao.MovieRepository;
import com.mth.model.Favorites;
import com.mth.model.Movie;
import com.mth.model.User;
import com.mth.service.MovieService;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {

	@Autowired
	private FavoritesRepository favoritesRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private MovieService movieService;

	@GetMapping("")
	public String favorites(HttpSession session, Model model) {
		User user = (User) session.getAttribute("loggedUser");

		if (user == null) {
			return "redirect:/login";
		}

		List<Movie> favoriteMovies = movieService.getFavoritesByUserId(user.getId());
		model.addAttribute("favoriteMovies", favoriteMovies);
		return "movies/favorites";
	}

	@GetMapping("/check-login")
	public ResponseEntity<?> checkLogin(HttpSession session) {
		User user = (User) session.getAttribute("loggedUser");
		if (user == null) {
			return ResponseEntity.status(401).body("User is not logged in");
		}
		return ResponseEntity.ok(user);
	}

	@PostMapping("/add")
	public ResponseEntity<?> addToFavorites(@RequestBody Map<String, Object> payload, HttpSession session) {
		try {
			User user = (User) session.getAttribute("loggedUser");
			if (user == null) {
				return ResponseEntity.status(401).body("User is not logged in");
			}

			if (payload.get("movie_id") == null) {
				return ResponseEntity.badRequest().body("movie_id is missing");
			}

			Long movieId = Long.valueOf(payload.get("movie_id").toString());

			Movie movie = movieRepository.findById(movieId).orElse(null);

			if (movie == null) {
				return ResponseEntity.badRequest().body("Invalid movie");
			}

			Favorites favorite = new Favorites();
			favorite.setUser(user);
			favorite.setMovie(movie);

			favoritesRepository.save(favorite);

			return ResponseEntity.ok("Movie added to favorites");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Internal Server Error");
		}
	}

	@GetMapping("/exists/{movieId}")
	public ResponseEntity<?> checkIfFavorite(@PathVariable Long movieId, HttpSession session) {
		User user = (User) session.getAttribute("loggedUser");
		if (user == null) {
			return ResponseEntity.status(401).body("User is not logged in");
		}

		Movie movie = movieRepository.findById(movieId).orElse(null);
		if (movie == null) {
			return ResponseEntity.badRequest().body("Invalid movie");
		}

		boolean exists = favoritesRepository.existsByUserAndMovie(user, movie);
		return ResponseEntity.ok(exists);
	}
}