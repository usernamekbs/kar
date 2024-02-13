package com.kick.web;


import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kick.dto.gallery.GalleryDto;
import com.kick.dto.gallery.GalleryViewDto;
import com.kick.dto.gallery.RequestGalleryDto;
import com.kick.service.GalleryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/gallery")
@RequiredArgsConstructor
public class GalleryController {
	
	private final GalleryService galleryService;
	
	//조회
	@GetMapping("/list")  
	public Page<GalleryDto> galleryList(Pageable pageable,
			@RequestParam(value="searchText", required=false) String searchText,
			@RequestParam(value="searchType", required=false) String searchType){
		return galleryService.galleryList(pageable,searchText,searchType);
	}
	
	//조회
	@GetMapping("/view/{id}")
	public GalleryViewDto galleryView(@PathVariable Long id){
		return galleryService.galleryView(id);
	}
	
	//입력
	@PostMapping("/create") 
	public GalleryDto gallerySave(@RequestPart(value="requestGalleryDto",required=false) RequestGalleryDto requestGalleryDto,
			@RequestParam(value = "file", required = false)MultipartFile file) throws IOException{
		return galleryService.gallerySave(requestGalleryDto,file);
	}
	
	//수정
	@PutMapping("/update") 
	public GalleryDto galleryUpdate(@RequestPart(value="requestGalleryDto",required=false) RequestGalleryDto requestGalleryDto,
			@RequestParam(value = "file", required = false)MultipartFile file) throws IOException{
		return galleryService.galleryUpdate(requestGalleryDto,file);
	}
	
	//삭제
	@DeleteMapping("/delete/{id}")
	public void galleryDelete(@PathVariable("id") long id){
		galleryService.galleryDelete(id);
	}
}
