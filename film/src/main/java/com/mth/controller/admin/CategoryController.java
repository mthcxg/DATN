package com.mth.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mth.model.Category;
import com.mth.service.CategoryService;

@Controller
@RequestMapping("admin/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public String index(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		Page<Category> categoryPage = categoryService.findAllCategories(page, size);
		model.addAttribute("categoryPage", categoryPage);
		model.addAttribute("newCategory", new Category());
		return "admin/category/index";
	}

	@PostMapping("/add")
	public String addCategory(@ModelAttribute("newCategory") Category category) {
		categoryService.saveCategory(category);
		return "redirect:/admin/category";
	}

	@PostMapping("/delete/{id}")
	public String deleteCategory(@PathVariable("id") Integer id) {
		categoryService.deleteCategoryById(id);
		return "redirect:/admin/category";
	}
}
