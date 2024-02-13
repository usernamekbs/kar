package com.kick.web.dto.likes;

import com.kick.entity.Likes;
import com.kick.entity.Post;
import com.kick.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestLikesDto {
	private Post post;
	private User user;
}
