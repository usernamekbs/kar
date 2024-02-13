package com.kick.web.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostSearchDto {
	private String searchSelect;
	private String searchTerm;
}
