package com.kick.dto;

import com.kick.CoupangDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CoupangDetailsDto {
	
	private String content;
	
	/*
	 * IMAGE	이미지
	 *  TEXT	텍스트
	 */
	//O
	private String detailType;
	
	public CoupangDetailsDto(CoupangDetails details){
		this.content = details.getContent();
		this.detailType = details.getDetailType();
		
	}
}
