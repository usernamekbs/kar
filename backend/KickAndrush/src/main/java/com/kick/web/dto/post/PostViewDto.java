package com.kick.web.dto.post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.kick.entity.Image;
import com.kick.entity.Post;
import com.kick.web.dto.comment.CommentDto;
import com.kick.web.dto.image.ImageDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class PostViewDto {
	private Long id;
	private String title;
	private String content;
	private String username;
	private int views;
	private List<CommentDto> comments = new ArrayList<>();
	private List<ImageDto> images = new ArrayList<>();
	private String createdDate;
	private String modifiedDate;
	
	public PostViewDto(Post post, List<CommentDto> commentsList, List<Image> imageList) {
		this.id 			= post.getId();
		this.title 			= post.getTitle();
		this.content 		= post.getContent();
		this.username		= post.getUser().getUsername();
		this.views 			= post.getViews();
		this.createdDate 	= post.getCreatedDate();
		this.modifiedDate	= post.getModifiedDate();
		this.comments		= commentsList;
		this.images			= imageList.stream().map(ImageDto::new).collect(Collectors.toList());
		
	}

}