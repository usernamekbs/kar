package com.kick.repository.comment;

import java.util.List;

import com.kick.entity.Comment;

public interface CustomCommentRepository {
	
	List<Comment> findAllList(Long postId);

}
