package com.kick.web.dto.login;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class AuthDto{
	 private String accessToken;
	 private String tokenType = "Bearer ";
	 private Long id;
	 private String username;
	 private String email;
	 private List<String> roles = new ArrayList<>();

	 public AuthDto(String accessToken,Long id,String username,List<String> roles) {
        this.accessToken = accessToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
	 }
}
