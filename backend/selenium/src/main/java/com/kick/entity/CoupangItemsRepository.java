package com.kick.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kick.CoupangItems;


public interface CoupangItemsRepository extends JpaRepository<CoupangItems, Long>{
//where i.coupang=:id
	@Query("SELECT i FROM CoupangItems i where i.coupang = :id") 
	List<CoupangItems> findAll(@Param("id") long id);
//	@Param("id") long id
}