package com.kick.web.dto.comment;

import java.util.ArrayList;
import java.util.List;

import com.kick.entity.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDto {
	
	private Long id;
	private String content;
	private String username;
	private Long parent;
	private List<CommentDto> children = new ArrayList<>();
	private Long postId;
	
	public CommentDto(Comment comment) {
		this.id = comment.getId();
		this.content = comment.getContent();
		this.username = comment.getUser().getUsername();
		this.postId = comment.getPost().getId();
		if(comment.getParent()!=null)
		this.parent = comment.getParent().getId();
	}

}
