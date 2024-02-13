package com.kick.repository.gallery;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.kick.entity.QCategory.category; 
import static com.kick.entity.QComment.comment;
import static com.kick.entity.QImage.image;
import static com.kick.entity.QPost.post;
import static com.kick.entity.QUser.user;
import static com.kick.entity.QGallery.gallery;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.kick.dto.gallery.GalleryDto;

@RequiredArgsConstructor
public class GalleryRepositoryImpl implements CustomGalleryRepository{
	
	private final JPAQueryFactory queryFactory;
	
	//querydsl 5.0.0부터는 pageresults사용이 불가하다 
	//또 result.size()로 전체개수를 가져올수 있지만 성능상 네트워크를 통한 데이터 전송이 많아
	//oom장애로 이어질수있다. 영속성 컨텍스트에 있는 total 카운터를 불러오자 
	@Override
	public Page<GalleryDto> findAllList(Pageable pageable,String searchText,String searchType) {
		List<GalleryDto> result = queryFactory
				.select(
					Projections.fields(GalleryDto.class,
	                        gallery.id.as("id"),
	                        gallery.title.as("title"),
	                        gallery.content.as("content"),
	                        gallery.filePath.as("filePath"),
	                        gallery.origName.as("origName"),
	                        gallery.views.as("views"),
	                        gallery.user.username.as("username"),
	                        gallery.categories.name.as("name")
					)
				) 
				.from(gallery)
				.leftJoin(gallery.categories,category)
				.leftJoin(gallery.user,user)  
				.where(
						(searchType.equals("Title") 	? titleEq(searchText) : null),
						(searchType.equals("Content") 	? contentEq(searchText) : null),
						(searchType.equals("Username") 	? usernameEq(searchText) : null)
					)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.orderBy(gallery.id.desc()) 
				.fetch();
		
		Long count = queryFactory
						.select(gallery.count())
						.from(gallery)
						.where(
								(searchType.equals("Title") 	? titleEq(searchText) : null),
								(searchType.equals("Content") 	? contentEq(searchText) : null),
								(searchType.equals("Username") 	? usernameEq(searchText) : null)
							)
						.fetchOne();
		
		return new PageImpl<>(result, pageable, count);
				
	}

	private BooleanExpression titleEq(String titleCond) {
		return titleCond != null ? gallery.title.contains(titleCond) : null;
	}
	
	private BooleanExpression contentEq(String contentCond) {
		return contentCond != null ? gallery.content.eq(contentCond) : null;
	}
	
	private BooleanExpression usernameEq(String usernameCond) {
		return usernameCond != null ? gallery.user.username.eq(usernameCond) : null;
	}
	
}
