package com.kick.service;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kick.dto.gallery.GalleryDto;
import com.kick.dto.gallery.GalleryViewDto;
import com.kick.dto.gallery.RequestGalleryDto;
import com.kick.entity.Category;
import com.kick.entity.Gallery;
import com.kick.entity.User;
import com.kick.repository.category.CategoryRepository;
import com.kick.repository.gallery.GalleryRepository;
import com.kick.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class GalleryService {
	private final String uploadDir = "C:\\source\\frontend\\kickandrush\\src\\thumb\\";
	private final GalleryRepository galleryRepository;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
	
	@Transactional(readOnly = true)
	public Page<GalleryDto> galleryList(Pageable pageable,String searchText,String searchType) {
		return galleryRepository.findAllList(pageable,searchText,searchType);
	}

	public GalleryDto gallerySave(RequestGalleryDto requestGalleryDto,MultipartFile file) throws IOException {
		User user=null;
		Category category=null;
		Gallery gallery=null;
		if(file!=null && !file.isEmpty()) {
			Path uploadPath = Paths.get(uploadDir);
			String uuid = UUID.randomUUID().toString();
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			
			if(!file.isEmpty()) {
				Files.createDirectories(uploadPath);
			}
			
			user = userRepository.findById(requestGalleryDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("해당 유저 번호가 없습니다. =" +requestGalleryDto.getUserId()));
			category = categoryRepository.findById(requestGalleryDto.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("해당 카테고리 번호가 없습니다. =" +requestGalleryDto.getCategoryId()));
			gallery = Gallery.builder().title(requestGalleryDto.getTitle())
							.content(requestGalleryDto.getContent())
							.origName(uuid+"."+file.getOriginalFilename())
							.contentType(file.getContentType())
							.filePath(uploadPath+"\\"+uuid+"."+file.getOriginalFilename())
							.fileSize(file.getSize())
							.user(user)
							.categories(category)
							.build();
			
			galleryRepository.save(gallery);
			try(InputStream inputStream = file.getInputStream()){
				Path filePath = uploadPath.resolve(uuid+"."+fileName); 
				Files.copy(inputStream,filePath ,StandardCopyOption.REPLACE_EXISTING);
			}catch(IOException e) {
				log.error("failed to upload error ", e);
			}
			
		}else {
			user = userRepository.findById(requestGalleryDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("해당 유저 번호가 없습니다. =" +requestGalleryDto.getUserId()));
			category = categoryRepository.findById(requestGalleryDto.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("해당 카테고리 번호가 없습니다. =" +requestGalleryDto.getCategoryId()));
			
			gallery = Gallery.builder()
							.title(requestGalleryDto.getTitle())
							.content(requestGalleryDto.getContent())
							.user(user)
							.categories(category)
							.build();
			galleryRepository.save(gallery);
		}
		
		return new GalleryDto(gallery);
	}
	
	@Transactional(readOnly = true)
	public GalleryViewDto galleryView(Long id) {
		Gallery gallery = galleryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
		
		return new GalleryViewDto(gallery);
	}

	public GalleryDto galleryUpdate(RequestGalleryDto requestGalleryDto, MultipartFile file) {
		Gallery gallery = galleryRepository.findById(requestGalleryDto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + requestGalleryDto.getId()));
		gallery.update(requestGalleryDto.getTitle(), requestGalleryDto.getContent());
		
		return new GalleryDto(gallery);
	}

	public void galleryDelete(long id) {
		galleryRepository.deleteById(id);
	}
	
}
