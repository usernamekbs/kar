package com.kick.web.dto.user;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kick.entity.Role;
import com.kick.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private Long id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String nickname;
	
	private List<Role> roles = new ArrayList<>(); 
	
	public UserDto(User user) {
		this.id 			= user.getId();
		this.username  		= user.getUsername();
		this.password		= user.getPassword();
		this.email 			= user.getEmail();
		this.nickname		= user.getNickname();
		for(Role r : user.getRoles()) {
			roles.add(r);
		}
	}
}
