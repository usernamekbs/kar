package com.kick.repository.comment;

import java.util.List;

import com.kick.entity.Comment;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import static com.kick.entity.QComment.comment;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CustomCommentRepository{
	
	private final JPAQueryFactory queryFactory;

	@Override
	public List<Comment> findAllList(Long postId) {
		List<Comment> results=queryFactory.selectFrom(comment)
                .leftJoin(comment.parent)
                .leftJoin(comment.post)
                .fetchJoin()
                .where(comment.post.id.eq(postId))
                .orderBy(comment.id.asc().nullsFirst())
                .fetch();
		return results;
		
	}

}
 