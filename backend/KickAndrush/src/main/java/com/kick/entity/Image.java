package com.kick.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="images")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Image{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="image_id")
	private Long id;
	
	private String filePath;
	
	private String origName;
	
	private String contentType;
	
	private Long fileSize;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="post_id")
	private Post post;
	
}
