package com.kick.web.dto.comment;

import lombok.Getter;

@Getter
public class RequestCommentDto {
	private String content;
	private Long userId;
	private Long postId;
	private Long parent;
}
