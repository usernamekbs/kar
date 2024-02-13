package com.kick.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kick.common.ResponseMessage;
import com.kick.entity.Comment;
import com.kick.entity.Post;
import com.kick.entity.User;
import com.kick.repository.comment.CommentRepository;
import com.kick.repository.post.PostRepository;
import com.kick.repository.user.UserRepository;
import com.kick.web.dto.comment.CommentDto;
import com.kick.web.dto.comment.RequestCommentDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

	private final CommentRepository commentRepository;
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	public CommentDto commentSave(RequestCommentDto requestCommentDto) {
		Post post = postRepository.findById(requestCommentDto.getPostId())
			.orElseThrow(() -> new IllegalArgumentException("해당 게시글 번호가 없습니다. =" +requestCommentDto.getPostId()));
		User user = userRepository.findById(requestCommentDto.getUserId())
				.orElseThrow(() -> new IllegalArgumentException("해당 유저 번호가 없습니다. =" +requestCommentDto.getUserId()));
			
		Comment parent = requestCommentDto.getParent() != null ? commentRepository.findById(requestCommentDto.getParent())
				.orElseThrow(() -> new IllegalArgumentException("해당 댓글의 부모가 없습니다. parent =" +requestCommentDto.getParent())) : null;
		
		Comment comment = Comment.builder()
								.content(requestCommentDto.getContent())
								.post(post)
								.user(user)
								.parent(parent)
								.build();
		
		commentRepository.save(comment);
		
		return new CommentDto(comment);
	}

	public CommentDto commentUpdate(Long id,RequestCommentDto requestCommentDto) {
		Comment comment = commentRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 게시글 번호가 없습니다. =" +id));
		comment.update(requestCommentDto.getContent());
		
		return new CommentDto(comment);
	}

	public ResponseMessage commentDelete(Long id) {
		commentRepository.deleteById(id);
		return new ResponseMessage("댓글이 삭제되었습니다.");
	}


}