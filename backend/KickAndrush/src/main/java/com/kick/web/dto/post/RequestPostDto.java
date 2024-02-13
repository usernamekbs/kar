package com.kick.web.dto.post;

import com.kick.entity.Category;
import com.kick.entity.Post;
import com.kick.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestPostDto {
	private String title;
	private String content;
	private Long userId;
	private Long categoryId;
	
	public Post toEntity(User user,Category category) {
		return new Post(title,content,user,category);
	}
}
