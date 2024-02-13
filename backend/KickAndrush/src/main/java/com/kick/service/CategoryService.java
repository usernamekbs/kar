package com.kick.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kick.entity.Category;
import com.kick.repository.category.CategoryRepository;
import com.kick.web.dto.category.CategoryDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
	
	private final CategoryRepository categoryRepository;

	public void categorySave() { 
		categoryRepository.save(null);
	}

	@Transactional(readOnly = true)
	public List<CategoryDto> categoryList() {
		List<Category> categories = categoryRepository.findAll();
		return categories.stream().map(CategoryDto::new).collect(Collectors.toList());
	}
}
