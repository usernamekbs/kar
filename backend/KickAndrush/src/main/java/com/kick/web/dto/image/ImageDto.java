package com.kick.web.dto.image;

import com.kick.entity.Image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
	private Long id;
	private String filePath;
	private String origName;
	private String contentType;
	private Long fileSize;
	private Long postId;

	public ImageDto(Image image) {
		this.id 			= image.getId();
		this.filePath		= image.getFilePath();
		this.origName		= image.getOrigName();
		this.contentType	= image.getContentType();
		this.fileSize		= image.getFileSize();
		this.postId			= image.getPost().getId();
	}
}
