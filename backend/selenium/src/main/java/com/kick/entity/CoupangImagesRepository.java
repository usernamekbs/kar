package com.kick.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kick.CoupangImages;
import com.kick.CoupangItems;

public interface CoupangImagesRepository extends JpaRepository<CoupangImages, Long>{

	@Query("SELECT i FROM CoupangImages i where i.id=:id")
	List<CoupangImages> findAll(@Param("id")long id);

}
