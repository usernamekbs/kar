package com.kick.web.dto.category;

import com.kick.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	
	private Long id;
	private String name;
	private String photo;
    private boolean enabled;
    
    public CategoryDto(Category category) {
		this.id 			= category.getId();
		this.name			= category.getName();
		this.photo			= category.getPhoto();
		this.enabled		= true;
	}
}
