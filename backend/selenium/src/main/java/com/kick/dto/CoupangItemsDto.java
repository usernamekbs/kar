package com.kick.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kick.CoupangContents;
import com.kick.CoupangDetails;
import com.kick.CoupangItems;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CoupangItemsDto {
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
	
	private List<CoupangImagesDto> images = new ArrayList<>();
	
	private List<CoupangAttributesDto> attributes= new ArrayList<>();
	
	private List<CoupangContentsDto> contents = new ArrayList<>();

	public CoupangItemsDto(CoupangItems item) {
		this.itemName 					= item.getItemName();
		this.originalPrice				= item.getOriginalPrice();
		this.salePrice					= item.getSalePrice();
		this.maximumBuyCount			= item.getMaximumBuyCount();
		this.maximumBuyForPerson		= item.getMaximumBuyForPerson();
		this.maximumBuyForPersonPeriod	= item.getMaximumBuyForPersonPeriod();
		this.outboundShippingTimeDay 	= item.getOutboundShippingTimeDay();
		this.unitCount					= item.getUnitCount();
		this.adultOnly					= item.getAdultOnly();
		this.taxType					= item.getTaxType();
		this.parallelImported			= item.getParallelImported();
		this.overseasPurchased			= item.getOverseasPurchased();
		this.pccNeeded					= item.getPccNeeded();
		this.images						= item.getImages().stream().map(CoupangImagesDto::new).collect(Collectors.toList());
		this.attributes					= item.getAttributes().stream().map(CoupangAttributesDto::new).collect(Collectors.toList());
		this.contents					= item.getContents().stream().map(CoupangContentsDto::new).collect(Collectors.toList());
		
	}
}
