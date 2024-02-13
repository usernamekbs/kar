package com.kick.dto;

import com.kick.CoupangImages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CoupangImagesDto {

	private int imageOrder;
	
	private String imageType;
	
	private String cdnPath;
	
	private String vendorPath;
	
	public CoupangImagesDto(CoupangImages images) {
		this.imageOrder = images.getImageOrder();
		this.imageType  = images.getImageType();
		this.cdnPath	= images.getCdnPath();
		this.vendorPath = images.getVendorPath();
	}
}
