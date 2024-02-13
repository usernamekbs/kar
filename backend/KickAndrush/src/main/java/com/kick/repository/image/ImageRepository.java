package com.kick.repository.image;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kick.entity.Image;



public interface ImageRepository extends JpaRepository<Image, UUID>{
	
	@Query("SELECT i FROM Image i WHERE i.post.id =:id")
	List<Image> findAll(@Param("id") long id);

}
