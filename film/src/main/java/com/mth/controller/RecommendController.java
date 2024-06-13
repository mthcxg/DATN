package com.mth.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mth.model.Movie;
import com.mth.model.User;
import com.mth.service.MovieService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class RecommendController {
	@Autowired
	private MovieService movieService;

	@RequestMapping("/rcm")
	public String rcm() {
		return "movies/recommend/recommend";
	}

	@RequestMapping("/rcmByFeeling")
	public String recommend() {
		return "movies/recommend/feeling";
	}

	@GetMapping("recommend")
	public String feeling(@RequestParam(value = "feeling", required = true) String feeling, Model model,
			HttpServletResponse response, HttpSession session) throws IOException {
		Movie movies;
		switch (feeling) {
		case "happy":
			movies = this.movieService.getMoviesByHappy();
			break;
		case "sad":
			movies = this.movieService.getMoviesBySad();
			break;
		case "bored":
			movies = this.movieService.getMoviesByBored();
			break;
		case "idk":
			movies = this.movieService.getMoviesByIdk();
			break;
		default:
			movies = new Movie();
			break;
		}
		model.addAttribute("movies", movies);
		return "movies/recommend/f";
	}

	@GetMapping("recommend/movie")
	public String movie(Model model, HttpServletResponse response, HttpSession session) throws IOException {
		return "movies/recommend/movie";
	}

	@GetMapping("/movie")
	public String movie(@RequestParam(value = "keyword", required = true) String keyword, Model model,
			HttpServletResponse response, HttpSession session) throws IOException, InterruptedException {
		clearFile("C:/Users/admin/Desktop/DATN/data/movie-name.txt");
		writeKeywordToFile(keyword, "C:/Users/admin/Desktop/DATN/data/movie-name.txt");
		String command = "cmd /c python C:/Users/admin/Desktop/DATN/data/test.py";
		try {
			Process process = Runtime.getRuntime().exec(command);
			int exitCode = process.waitFor();
			if (exitCode == 0) {
				List<List<String>> records = new ArrayList<>();
				try (BufferedReader br = new BufferedReader(
						new FileReader("C:/Users/admin/Desktop/DATN/data/result.csv"))) {
					String line;
					while ((line = br.readLine()) != null) {
						String[] values = line.split(",");
						records.add(Arrays.asList(values));
					}
				}
				List<Movie> movies = new ArrayList<>();
				for (List<String> record : records) {
					try {
						int id = Integer.parseInt(record.get(0));
						System.out.println(id);
						Movie movie = this.movieService.getReferenceById(id);
						movies.add(movie);
					} catch (NumberFormatException e) {
						System.out.println("Không thể chuyển đổi giá trị thành số nguyên: " + record.get(0));
					}
				}

				model.addAttribute("movies", movies);
				model.addAttribute("keyword", keyword);
				return "movies/recommend/index";
			} else {
				throw new RuntimeException("Lỗi khi chạy file Python");
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Lỗi khi chạy file Python");
		}
	}

	private void writeKeywordToFile(String keyword, String filePath) throws IOException {
		File file = new File(filePath);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(keyword);
		}
	}

	private void clearFile(String filePath) throws IOException {
		File file = new File(filePath);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

		}
	}

	@RequestMapping("/rcmByFavorite")
	public String favorite(Model m, HttpSession session) throws IOException, InterruptedException {
		User loggedUser = (User) session.getAttribute("loggedUser");
		if (loggedUser == null) {
			return "redirect:/login";
		}
		List<Movie> favoriteMovies = movieService.getFavoritesByUserId(loggedUser.getId());

		String filePath = "C:\\Users\\admin\\Desktop\\DATN\\data\\favorite.txt";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			for (Movie movie : favoriteMovies) {
				writer.write(movie.getName() + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String command = "cmd /c python C:/Users/admin/Desktop/DATN/data/favorite.py";
		try {
			Process process = Runtime.getRuntime().exec(command);
			int exitCode = process.waitFor();
			if (exitCode == 0) {
				List<List<String>> records = new ArrayList<>();
				try (BufferedReader br = new BufferedReader(
						new FileReader("C:/Users/admin/Desktop/DATN/data/favorite-result.csv"))) {
					String line;
					while ((line = br.readLine()) != null) {
						String[] values = line.split(",");
						records.add(Arrays.asList(values));
					}
				}
				List<Movie> movies = new ArrayList<>();
				for (List<String> record : records) {
					try {
						int id = Integer.parseInt(record.get(0));
						System.out.println(id);
						Movie movie = this.movieService.getReferenceById(id);
						movies.add(movie);
					} catch (NumberFormatException e) {
						System.out.println("Không thể chuyển đổi giá trị thành số nguyên: " + record.get(0));
					}
				}

				m.addAttribute("movies", movies);
				return "movies/recommend/favorite";
			} else {
				throw new RuntimeException("Lỗi khi chạy file Python");
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Lỗi khi chạy file Python");
		}
	}
}
