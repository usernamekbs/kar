package com.kick.repository.post;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.kick.web.dto.post.PostDto;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.kick.entity.QPost.post; 
import static com.kick.entity.QCategory.category; 
import static com.kick.entity.QComment.comment;
import static com.kick.entity.QImage.image;
import static com.kick.entity.QUser.user;

@RequiredArgsConstructor
public class PostRepositoryImpl implements CustomPostRepository{
	
	private final JPAQueryFactory queryFactory;

	//querydsl 5.0.0부터는 pageresults사용이 불가하다 
	//또 result.size()로 전체개수를 가져올수 있지만 성능상 네트워크를 통한 데이터 전송이 많아
	//oom장애로 이어질수있다. 영속성 컨텍스트에 있는 total 카운터를 불러오자 
	@Override
	public Page<PostDto> findAllList(Pageable pageable,String searchText,String searchType) {
		List<PostDto> result = queryFactory
				.select(
					Projections.fields(PostDto.class,
	                        post.id.as("id"),
	                        post.title.as("title"),
	                        post.content.as("content"),
	                        post.user.username.as("username"),
	                        post.views.as("views"),
	                        post.categories.id.as("categoryId"),
	                        post.createdDate,
	                        post.modifiedDate,
	                        post.categories.name.as("name"),
	                        post.categories.enabled.as("enabled"),
	                        post.categories.photo.as("photo"),
	                        ExpressionUtils.as(
	                            JPAExpressions
	                                .select(comment.count()) 
	                                .from(comment)
	                                .where(comment.post.id.eq(post.id))
	                                , "commentCnt"
	                        ),
	                        ExpressionUtils.as(
	                            JPAExpressions
	                                .select(image.count()) 
	                                .from(image)
	                                .where(image.post.id.eq(post.id))
	                                , "imageCnt"
	                        )
					)
				) 
				.from(post)
				.leftJoin(post.categories,category)
				.leftJoin(post.user,user)
				.where(
					(searchType.equals("Title") 	? titleEq(searchText) : null),
					(searchType.equals("Content") 	? contentEq(searchText) : null),
					(searchType.equals("Username") 	? usernameEq(searchText) : null)
				)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.orderBy(post.id.desc())
				.fetch();
		
		Long count = queryFactory
						.select(post.count())
						.from(post)
						.where(
							(searchType.equals("Title") 	? titleEq(searchText) : null),
							(searchType.equals("Content") 	? contentEq(searchText) : null),
							(searchType.equals("Username") 	? usernameEq(searchText) : null)
						)
						.fetchOne();
		return new PageImpl<>(result, pageable, count);
	}

	private BooleanExpression titleEq(String titleCond) {
		return titleCond != null ? post.title.contains(titleCond) : null;
	}
	
	private BooleanExpression contentEq(String contentCond) {
		return contentCond != null ? post.content.eq(contentCond) : null;
	}
	
	private BooleanExpression usernameEq(String usernameCond) {
		return usernameCond != null ? post.user.username.eq(usernameCond) : null;
	}
}
