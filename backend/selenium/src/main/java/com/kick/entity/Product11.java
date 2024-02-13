package com.kick.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="products11")
@NoArgsConstructor
@Getter
@Setter
public class Product11{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="products_id")
	private Long id;
	
	@Column(length = 100000)
	private String option;
	
	@Column(length = 100000)
	private String price;
	
	@Column(length = 100000)
	private String realPrice;
	@Column(length = 100000)
	
	private String company;
	@Column(length = 100000)
    private String title;
    
	@Column(length = 100000)
    private String thmb;
	
	@Column(length = 100000)
    private String detailImg;
	
	@Column(length = 100000)
    private String detailImg2;
	
	private int stockQty;
	
	private String optionQty;
}