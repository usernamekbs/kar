package com.kick.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name="users")
@Getter
@Builder
@Setter
public class User{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long id;
	
	@Column(unique = true)
	private String username;
	
	private String password; 
	
	private String nickname;
	
	private String email;
	
	@ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
	@Builder.Default
    private List<Role> roles = new ArrayList<>();
	
	public void updateUser(String username,String nickname,String email) {
		this.username = username;
		this.nickname = nickname;
		this.email    = email;
	}
	
	public void updateCommentUser(String username) {
		System.out.println(username);
		this.username = username;
	}
	
	public void addRole(Role role) {
		roles.add(role);
    }

}