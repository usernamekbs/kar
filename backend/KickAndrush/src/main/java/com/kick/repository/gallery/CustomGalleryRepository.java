package com.kick.repository.gallery;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kick.dto.gallery.GalleryDto;

public interface CustomGalleryRepository {
	Page<GalleryDto> findAllList(Pageable pageable,String searchText,String searchType);
}
