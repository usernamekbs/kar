package com.kick.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kick.Coupang;

public interface CoupangRepository extends JpaRepository<Coupang, Long>{

	@Query("SELECT c FROM Coupang c where c.id=:id")
	Optional<Coupang> findAllList(@Param("id")long id);

}