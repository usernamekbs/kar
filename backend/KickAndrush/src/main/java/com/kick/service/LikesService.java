package com.kick.service;

import java.util.Optional;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kick.entity.Likes;
import com.kick.entity.Post;
import com.kick.entity.User;
import com.kick.repository.like.LikesRepository;
import com.kick.repository.post.PostRepository;
import com.kick.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LikesService {
	
	private final LikesRepository likesRepository;
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	
	public Long likeSave(long postId,long userId) {
		 	Optional<Post> post = postRepository.findById(postId);  // 1
	        if (post.isEmpty()) {
	        	System.out.println("없어");
	        }
	        Optional<User> user = userRepository.findById(userId);  // 1
	        
	        // 이전에 좋아요 누른 적 있는지 확인
	        Optional<Likes> found = likesRepository.findByPostAndUser(post.get(), user.get());

	        if (found.isEmpty()) {  // 좋아요 누른적 없음
	            Likes likes = Likes.builder()
	            					.post(post.get())
	            					.user(user.get())
	            					.build();
	            likesRepository.save(likes);  // 3
	        } else { // 좋아요 누른 적 있음
	            likesRepository.delete(found.get()); // 좋아요 눌렀던 정보를 지운다.
	        }
			return likesRepository.countPostAndUser(postId);
	}

	@Transactional(readOnly=true)
	public Long likeList(Long postId) {
		return likesRepository.countPostAndUser(postId);
	}
}