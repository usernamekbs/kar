package com.kick.repository.like;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kick.entity.Likes;
import com.kick.entity.Post;
import com.kick.entity.User;

public interface LikesRepository extends JpaRepository<Likes, Long>{

//	@Query("SELECT count(l) FROM Likes l WHERE l.post.id=:postId AND l.user.id=:userId") 
//	long asd(Long postId,Long userId);

	Optional<Likes> findByPostAndUser(Post post, User user);

	@Query("SELECT count(l) FROM Likes l WHERE l.post.id=:postId") 
	Long countPostAndUser(@Param("postId") long postId);

}
