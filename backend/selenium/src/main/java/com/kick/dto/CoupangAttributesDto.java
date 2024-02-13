package com.kick.dto;

import com.kick.CoupangAttributes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CoupangAttributesDto {
	
	private String attributeTypeName;
	
	private String attributeValueName;
	
	public CoupangAttributesDto(CoupangAttributes attributes) {
		this.attributeTypeName 	= attributes.getAttributeTypeName();
		this.attributeValueName	= attributes.getAttributeValueName();
	}
}
