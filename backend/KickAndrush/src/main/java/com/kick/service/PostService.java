package com.kick.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kick.entity.Category;
import com.kick.entity.Comment;
import com.kick.entity.Image;
import com.kick.entity.Post;
import com.kick.entity.User;
import com.kick.repository.category.CategoryRepository;
import com.kick.repository.comment.CommentRepository;
import com.kick.repository.image.ImageRepository;
import com.kick.repository.like.LikesRepository;
import com.kick.repository.post.PostRepository;
import com.kick.repository.user.UserRepository;
import com.kick.web.dto.comment.CommentDto;
import com.kick.web.dto.post.PostDto;
import com.kick.web.dto.post.PostViewDto;
import com.kick.web.dto.post.RequestPostDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostService {
	private final String uploadDir = "C:\\source\\frontend\\kickandrush\\src\\upload\\";
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final CommentRepository commentRepository;
	private final CategoryRepository categoryRepository;
	private final ImageRepository imageRepository;
	
	@Transactional(readOnly = true)
	public Page<PostDto> postList(Pageable pageable,String searchText,String searchType) {
		//pageImple은 list pageable totalcount를 넣어줘야한다.
		return postRepository.findAllList(pageable,searchText,searchType);
	}
	
	public PostViewDto postView(Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
		post.updateViews();
		List<CommentDto> commentList = addChildComment(commentRepository.findAllList(id));
		List<Image> imageList = imageRepository.findAll(id);
		return new PostViewDto(post,commentList,imageList);
	}

	public PostDto postSave(RequestPostDto requestPostDto,List<MultipartFile> files) throws IOException {
		Category category= categoryRepository.findById(requestPostDto.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("해당 카테고리 번호가 없습니다. =" +requestPostDto.getCategoryId()));
		User user = userRepository.findById(requestPostDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("해당 유저 번호가 없습니다. =" +requestPostDto.getUserId()));
		Post post = requestPostDto.toEntity(user,category);
		postRepository.save(post);
		
		if(files!=null && !files.isEmpty()) {
			Path uploadPath =Paths.get(uploadDir);
			for(MultipartFile mf : files) {
				String uuid = UUID.randomUUID().toString();
				String fileName = StringUtils.cleanPath(mf.getOriginalFilename());
				if(!mf.isEmpty()) {
					Files.createDirectories(uploadPath);
				}

				try(InputStream inputStream = mf.getInputStream()){
					Path filePath = uploadPath.resolve(uuid+"."+fileName); 
					Files.copy(inputStream,filePath ,StandardCopyOption.REPLACE_EXISTING);
				}catch(IOException e) {
					log.error("failed to upload error ", e);
				}
				Image image = Image.builder()
						.filePath(uploadDir+mf.getOriginalFilename())
						.origName(uuid+"."+mf.getOriginalFilename())
						.contentType(mf.getContentType())
						.fileSize(mf.getSize())
						.post(post)
						.build();
				
				imageRepository.save(image);  
			} 
		}
	
		return new PostDto(post);
	}
	
	public PostDto postUpdate(RequestPostDto requestPostDto,Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
		post.update(requestPostDto.getTitle(), requestPostDto.getContent());
		return new PostDto(post);
	}

	public void postDelete(Long id) {
		postRepository.deleteById(id);
	}

	private List<CommentDto> addChildComment(List<Comment> commentList) {
		List<CommentDto> result = new ArrayList<>();
    	Map<Long, CommentDto> map = new HashMap<>(); 
    	commentList.stream().forEach(c -> {
    		 CommentDto dto = new CommentDto(c);
             map.put(dto.getId(), dto);
             if(c.getParent() != null) 
            	 map.get(c.getParent().getId()).getChildren().add(dto);
        	 else {
    			 result.add(dto);
    		 }
    	});
		return result;
	}

}