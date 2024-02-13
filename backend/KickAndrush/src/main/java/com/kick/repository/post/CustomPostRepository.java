package com.kick.repository.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kick.web.dto.post.PostDto;

public interface CustomPostRepository {
	
	Page<PostDto> findAllList(Pageable pageable,String searchText,String searchType);

}
