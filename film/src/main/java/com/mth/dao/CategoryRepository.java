package com.mth.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mth.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	@Query(value = "select * from category", nativeQuery = true)
	public List<Category> getCategories();

	@Query(value = "select * from category where name = ?1", nativeQuery = true)
	public Category getId(@Param("name") String title);

	@Query(value = "select * from category where id =?1", nativeQuery = true)
	public Category getReferenceById(Integer id);

	@Query(value = "SELECT c.* FROM movie_categories mc JOIN category c ON mc.categoryId=c.id WHERE mc.movieId=:movie_id", nativeQuery = true)
	public List<Category> getCategoriesForMovie(@Param("movie_id") Integer movie_id);

	Page<Category> findAll(Pageable pageable);

}
