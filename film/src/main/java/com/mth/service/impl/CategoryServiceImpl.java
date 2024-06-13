package com.mth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mth.dao.CategoryRepository;
import com.mth.model.Category;
import com.mth.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getCategories() {
		return categoryRepository.getCategories();
	}

	@Override
	public Category getId(String title) {
		return categoryRepository.getId(title);
	}

	public Category getReferenceById(Integer id) {
		return categoryRepository.getReferenceById(id);
	}

	@Override
	public List<Category> getCategoriesForMovie(Integer movie_id) {
		return categoryRepository.getCategoriesForMovie(movie_id);
	}

	@Override
	public void saveCategory(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public Page<Category> findAllCategories(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return categoryRepository.findAll(pageable);
	}

	@Override
	public void deleteCategoryById(Integer id) {
		categoryRepository.deleteById(id);
	}
}
