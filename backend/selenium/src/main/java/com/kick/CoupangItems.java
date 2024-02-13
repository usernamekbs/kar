package com.kick;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CoupangItems {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="items_id")
	private Long id; 
	//O
	private String itemName;
	//O
	private int originalPrice;
	//O
	private int salePrice;
	//O
	private int maximumBuyCount;
	//O
	private int maximumBuyForPerson;
	//O
	private int maximumBuyForPersonPeriod;
	//O
	private int outboundShippingTimeDay;
	//O
	private int unitCount;
	//O
	private String adultOnly;
	//O
	private String taxType;
	//O
	private String parallelImported;
	//O
	private String overseasPurchased;
	//O
	private Boolean pccNeeded;
	
	private List<String> searchTags = new ArrayList<>();
//	//O
	@OneToMany(mappedBy="coupangItems", cascade = CascadeType.ALL)
	private List<CoupangImages> images = new ArrayList<>();
//	//O

	@OneToMany(mappedBy="coupangItems", cascade = CascadeType.ALL)
	private List<CoupangAttributes> attributes = new ArrayList<>();
	//O
	@OneToMany(mappedBy="coupangItems", cascade = CascadeType.ALL)
	private List<CoupangContents> contents = new ArrayList<>();
//////	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="coupang_id")
	private Coupang coupang;
////	
//	
	public void images(List<CoupangImages> images) {
		this.images.addAll(images);
	}
	
	public void contents(CoupangContents contents) {
		this.contents.add(contents);
	}

	public void attributes(CoupangAttributes attributes) {
		this.attributes.add(attributes);
	}

	public void searchTags(String tags) {
		this.searchTags.add(tags);
	}
}
