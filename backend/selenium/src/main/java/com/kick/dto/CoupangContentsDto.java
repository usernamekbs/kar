package com.kick.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kick.CoupangContents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CoupangContentsDto {

	private String contentsType;
	//O
	private List<CoupangDetailsDto> contentDetails = new ArrayList<>();
	
	public CoupangContentsDto(CoupangContents content) {
		this.contentsType = content.getContentsType();
		this.contentDetails = content.getContentDetails().stream().map(CoupangDetailsDto::new).collect(Collectors.toList());
	}
}
