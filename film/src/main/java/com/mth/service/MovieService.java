package com.mth.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.mth.model.CommentInfo;
import com.mth.model.Movie;

@Service
public interface MovieService {

	List<Movie> getMovies();

	Movie getReferenceById(Integer id);

	List<Movie> getMoviesByCategory(Integer category_id);

	List<Movie> searchAllCategories(@Param("query") String query);

	List<Movie> searchByCategory(@Param("categoryId") Integer categoryId, @Param("query") String query);

	List<Movie> sortByRating(@Param("categoryId") Integer categoryId);

	List<Movie> getMoviesByCategoryAndSortedByRating(Integer category_id);

	List<Movie> getHotMovie();

	List<Movie> getRandomMovies();

	List<Movie> getHotestMovies();

	List<Movie> getMoviesByActor(Integer actorId);

	List<Movie> getMoviesByActorAndSortedByRating(Integer actorId);

	List<Movie> getDirector(@Param("query") String query);

	List<Movie> getDirectorAndSortedByRating(@Param("query") String query);

	List<Movie> getDirectorForMovies(@Param("movie_id") Integer movie_id);

	Movie getMoviesByHappy();

	Movie getMoviesBySad();

	Movie getMoviesByBored();

	Movie getMoviesByIdk();

	public List<Movie> getFavoritesByUserId(Long userId);

	public Page<Movie> findAllMovies(int page, int size);

	public void saveMovie(Movie movie);

	public void deleteMovieById(Long id);

	public Page<Movie> searchMovies(String search, int page, int size);

	public Map<String, Long> countMoviesByCategory();

	public Optional<Movie> findById(long id);

	public Integer countComments(Integer movieId);
	
	public List<CommentInfo> getCommentByMovieId(Integer movie_id);
}
