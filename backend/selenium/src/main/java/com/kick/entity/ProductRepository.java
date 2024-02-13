package com.kick.entity;

import org.openqa.selenium.WebElement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{

	String findById(WebElement productCode);


}
