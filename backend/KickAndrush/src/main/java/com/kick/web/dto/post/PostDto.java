package com.kick.web.dto.post;

import java.time.LocalDateTime;

import com.kick.entity.Post;
import com.kick.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	private Long id; 
	private String title;
	private String content;
	private String username;
	private int views;
	private String name;
	private Long categoryId;
	private boolean enabled;
	private String photo;
	private Long commentCnt;
	private String createdDate;
	private String modifiedDate;
	private Long imageCnt;
	
	public PostDto(Post post) {
		this.id 			= post.getId();
		this.title 			= post.getTitle();
		this.content 		= post.getContent();
		this.views 			= post.getViews();
		this.name 			= post.getCategories().getName();
		this.enabled 		= post.getCategories().isEnabled();
		this.photo			= post.getCategories().getPhoto();
		this.commentCnt		= post.getCommentCnt();
		this.categoryId		= post.getCategories().getId();
		this.imageCnt		= post.getImageCnt();
		this.createdDate 	= post.getCreatedDate();
		this.modifiedDate	= post.getModifiedDate();
	}

}


