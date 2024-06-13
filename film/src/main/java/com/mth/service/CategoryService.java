package com.mth.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mth.model.Category;

public interface CategoryService {

	public List<Category> getCategories();

	public Category getReferenceById(Integer id);

	public Category getId(String categoryName);

	List<Category> getCategoriesForMovie(Integer movie_id);

	public Page<Category> findAllCategories(int page, int size);

	public void saveCategory(Category category);

	public void deleteCategoryById(Integer id);
}
