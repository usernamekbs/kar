package com.kick.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kick.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>,CustomPostRepository{


}
