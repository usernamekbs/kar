package com.kick.web.dto.likes;

import com.kick.entity.Likes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LikesDto {
	private Long postId;
	private Long userId;
}
