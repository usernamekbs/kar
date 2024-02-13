package com.kick.repository.gallery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kick.entity.Gallery;

public interface GalleryRepository extends JpaRepository<Gallery, Long>,CustomGalleryRepository{


}
