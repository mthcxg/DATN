package com.mth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mth.dao.MovieRepository;
import com.mth.model.CommentInfo;
import com.mth.model.Movie;
import com.mth.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	@Autowired
	private MovieRepository movieRepository;
	@Override
	public List<Movie> getMovies() {
		return movieRepository.getMovies();
	}

	@Override
	public Movie getReferenceById(Integer id) {
		return movieRepository.getReferenceById(id);
	}

	@Override
	public List<Movie> getMoviesByCategory(Integer categoryId) {
		return movieRepository.getMoviesByCategory(categoryId);
	}

	@Override
	public List<Movie> searchAllCategories(String query) {
		return movieRepository.searchAllCategories(query);
	}

	@Override
	public List<Movie> searchByCategory(Integer categoryId, String query) {
		return movieRepository.searchByCategory(categoryId, query);
	}

	@Override
	public List<Movie> getHotMovie() {
		return movieRepository.getHotMovie();
	}

	@Override
	public List<Movie> getMoviesByCategoryAndSortedByRating(Integer category_id) {
		return movieRepository.getMoviesByCategoryAndSortedByRating(category_id);
	}

	@Override
	public List<Movie> getRandomMovies() {
		return movieRepository.getRandomMovies();
	}

	@Override
	public List<Movie> getHotestMovies() {
		return movieRepository.getHotestMovies();
	}

	@Override
	public List<Movie> getMoviesByActor(Integer actorId) {
		return movieRepository.getMoviesByActor(actorId);
	}

	@Override
	public List<Movie> getMoviesByActorAndSortedByRating(Integer actorId) {
		return movieRepository.getMoviesByActorAndSortedByRating(actorId);
	}

	@Override
	public List<Movie> getDirector(String query) {
		return movieRepository.getDirector(query);
	}

	@Override
	public List<Movie> getDirectorAndSortedByRating(String query) {
		return movieRepository.getDirectorAndSortedByRating(query);
	}

	@Override
	public List<Movie> getDirectorForMovies(Integer movie_id) {
		return movieRepository.getDirectorForMovies(movie_id);
	}

	@Override
	public Movie getMoviesByHappy() {
		return movieRepository.getMoviesByHappy();
	}

	@Override
	public Movie getMoviesBySad() {
		return movieRepository.getMoviesBySad();
	}

	@Override
	public Movie getMoviesByBored() {
		return movieRepository.getMoviesByBored();
	}

	@Override
	public Movie getMoviesByIdk() {
		return movieRepository.getMoviesByIdk();
	}

	@Override
	public Page<Movie> findAllMovies(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return movieRepository.findAll(pageable);
	}

	@Override
	public void saveMovie(Movie movie) {
		movieRepository.save(movie);

	}

	@Override
	public void deleteMovieById(Long id) {
		movieRepository.deleteById(id);
	}

	public Page<Movie> searchMovies(String search, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return movieRepository.searchByKeyword(search, pageable);
	}

	@Override
	public Map<String, Long> countMoviesByCategory() {
		List<Object[]> results = movieRepository.countMoviesByCategory();
		Map<String, Long> categoryCounts = new HashMap<>();
		for (Object[] result : results) {
			String category = (String) result[0];
			Long count = (Long) result[1];
			categoryCounts.put(category, count);
		}
		return categoryCounts;
	}

	@Override
	public List<Movie> getFavoritesByUserId(Long userId) {
		return movieRepository.findFavoritesByUserId(userId);
	}

	@Override
	public Optional<Movie> findById(long id) {
		return movieRepository.findById(id);
	}

	@Override
	public Integer countComments(Integer movieId) {
		return movieRepository.countComments(movieId);
	}

	@Override
	public List<CommentInfo> getCommentByMovieId(Integer movie_id) {
		return movieRepository.getCommentByMovieId(movie_id);
	}

	@Override
	public List<Movie> sortByRating(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

}
