package com.kick.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kick.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>,CustomCommentRepository{


}
