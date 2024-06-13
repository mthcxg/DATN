package com.mth.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mth.model.CommentInfo;
import com.mth.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
	@Query(value = "select * from Movies", nativeQuery = true)
	public List<Movie> getMovies();

	@Query(value = "select * from Movies where id = :movie_id", nativeQuery = true)
	public List<Movie> getDirectorForMovies(@Param("movie_id") Integer movie_id);

	@Query(value = "SELECT * FROM movies ORDER BY  rate DESC LIMIT 8;", nativeQuery = true)
	public List<Movie> getHotestMovies();

	@Query(value = "SELECT * FROM movies  ORDER BY RAND() LIMIT 8;", nativeQuery = true)
	public List<Movie> getRandomMovies();

	@Query(value = "SELECT * FROM movies WHERE director = :query", nativeQuery = true)
	public List<Movie> getDirector(@Param("query") String query);

	@Query(value = "SELECT * FROM movies WHERE director = :query ORDER BY rate DESC, num_rate DESC", nativeQuery = true)
	public List<Movie> getDirectorAndSortedByRating(@Param("query") String query);

	@Query(value = "select * from Movies where id =?1", nativeQuery = true)
	public Movie getReferenceById(Integer id);

	@Query(value = "SELECT m.*\r\n" + "FROM movies m\r\n" + "INNER JOIN movie_categories mc ON m.id = mc.movieId\r\n"
			+ "WHERE mc.categoryId = :category_id", nativeQuery = true)
	public List<Movie> getMoviesByCategory(Integer category_id);

	@Query(value = "SELECT m.*\r\n" + "FROM movies m\r\n" + "INNER JOIN movie_categories mc ON m.id = mc.movieId\r\n"
			+ "WHERE mc.categoryId = :category_id ORDER BY rate DESC, num_rate DESC", nativeQuery = true)
	public List<Movie> getMoviesByCategoryAndSortedByRating(Integer category_id);

	@Query(value = "SELECT * FROM movies WHERE LOWER(name) LIKE %:query%", nativeQuery = true)
	List<Movie> searchAllCategories(@Param("query") String query);

	@Query(value = "SELECT m.* \r\n" + "FROM movies m\r\n" + "JOIN movie_categories mc ON m.id = mc.movieId\r\n"
			+ "WHERE mc.categoryId = :categoryId\r\n" + "  AND LOWER(m.name) LIKE %:query%\r\n", nativeQuery = true)
	List<Movie> searchByCategory(@Param("categoryId") Integer categoryId, @Param("query") String query);

	@Query(value = "SELECT * FROM movies\r\n" + "ORDER BY rate DESC limit 100;", nativeQuery = true)
	List<Movie> getHotMovie();

	@Query(value = "SELECT m.*\r\n" + "FROM movies m\r\n" + "INNER JOIN movie_actors mc ON m.id = mc.movieId\r\n"
			+ "WHERE mc.actorId = :actorId", nativeQuery = true)
	public List<Movie> getMoviesByActor(Integer actorId);

	@Query(value = "SELECT m.*\r\n" + "FROM movies m\r\n" + "INNER JOIN movie_actors mc ON m.id = mc.movieId\r\n"
			+ "WHERE mc.actorId = :actorId ORDER BY rate DESC, num_rate DESC", nativeQuery = true)
	public List<Movie> getMoviesByActorAndSortedByRating(Integer actorId);

	@Query(value = "SELECT m.* FROM movies m JOIN favorites f ON m.id = f.movie_id WHERE f.user_id = :userId", nativeQuery = true)
	public List<Movie> findFavoritesByUserId(Long userId);

	@Query(value = "SELECT m.* FROM movies m INNER JOIN movie_categories mc ON m.id = mc.movieId WHERE mc.categoryId IN ( SELECT c.id FROM category c WHERE c.name IN ('Romance', 'Fantasy', 'Drama', 'Family', 'Musical', 'Animation') ) ORDER BY RAND() LIMIT 1;", nativeQuery = true)
	public Movie getMoviesByHappy();

	@Query(value = "SELECT m.* FROM movies m INNER JOIN movie_categories mc ON m.id = mc.movieId WHERE mc.categoryId IN ( SELECT c.id FROM category c WHERE c.name IN ('Comedy', 'Adventure', 'Action', 'Music') ) ORDER BY RAND() LIMIT 1;", nativeQuery = true)
	public Movie getMoviesBySad();

	@Query(value = "SELECT m.* FROM movies m INNER JOIN movie_categories mc ON m.id = mc.movieId WHERE mc.categoryId IN ( SELECT c.id FROM category c WHERE c.name IN ('Sci-Fi', 'Sport', 'History', 'Crime', 'War', 'Mystery') ) ORDER BY RAND() LIMIT 1;", nativeQuery = true)
	public Movie getMoviesByBored();

	@Query(value = "SELECT m.* FROM movies m INNER JOIN movie_categories mc ON m.id = mc.movieId WHERE mc.categoryId IN ( SELECT c.id FROM category c WHERE c.name IN ('Horror', 'Western', 'Thriller', 'Film-Noir', 'Documentary', 'Biography') ) ORDER BY RAND() LIMIT 1;", nativeQuery = true)
	public Movie getMoviesByIdk();

	public Optional<Movie> findById(long id);

	boolean existsById(Long id);

	void deleteById(Long Id);

	Page<Movie> findByNameContaining(String name, Pageable pageable);

	Page<Movie> findAll(Pageable pageable);

	@Query("SELECT m FROM Movie m WHERE " + "LOWER(m.name) LIKE LOWER(CONCAT('%', :search, '%')) OR "
			+ "LOWER(m.description) LIKE LOWER(CONCAT('%', :search, '%')) OR "
			+ "LOWER(m.director) LIKE LOWER(CONCAT('%', :search, '%'))")
	Page<Movie> searchByKeyword(String search, Pageable pageable);

	@Query(value = "SELECT c.name, COUNT(mc.movieId) FROM movie_categories mc  JOIN Category c ON mc.categoryId = c.id GROUP BY c.name", nativeQuery = true)
	List<Object[]> countMoviesByCategory();

	@Query(value = "SELECT COUNT(*) FROM comments WHERE movie_id = :movieId", nativeQuery = true)
	public Integer countComments(Integer movieId);

	@Query(value = "SELECT c.description, c.rate, u.username, u.avatar FROM comments c\r\n"
			+ "JOIN user u ON c.user_id = u.id\r\n" + "WHERE c.movie_id = :movie_id", nativeQuery = true)
	public List<CommentInfo> getCommentByMovieId(Integer movie_id);

}
