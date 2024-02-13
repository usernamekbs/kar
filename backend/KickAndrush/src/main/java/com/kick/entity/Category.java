package com.kick.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.kick.common.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="categories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Category extends BaseTimeEntity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="categories_id")
	private Long id;
	
	@Column(length = 128, nullable = false)
	private String name;
	
	private String photo;
	
	@Column(nullable = false)
    private boolean enabled;
	
	@OneToMany(mappedBy = "categories")
    private List<Post> posts = new ArrayList<>();

	@OneToMany(mappedBy = "categories")
    private List<Gallery> gallerys = new ArrayList<>();
}
