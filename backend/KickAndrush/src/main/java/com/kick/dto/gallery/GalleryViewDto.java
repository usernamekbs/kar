package com.kick.dto.gallery;

import com.kick.entity.Gallery;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GalleryViewDto {
	
	private Long id;
	private String title;
	private String content;  
	private String origName;
	private String username; 
	private int views;
	 
	public GalleryViewDto(Gallery gallery) {
		this.id 		= gallery.getId();
		this.title 		= gallery.getTitle();
		this.content 	= gallery.getContent();
		this.origName   = gallery.getOrigName();
		this.username   = gallery.getUser().getUsername();
		this.views		= gallery.getViews();
	}

}
