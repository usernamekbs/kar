package com.kick.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kick.service.CategoryService;
import com.kick.web.dto.category.CategoryDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
	
	private final CategoryService categoryService;
	
	//CategoryDto Category
	public void categorySave() {
		categoryService.categorySave();
	}
	
	@GetMapping("/list")
	public List<CategoryDto> categoryList() {
		return categoryService.categoryList();
	}
}
