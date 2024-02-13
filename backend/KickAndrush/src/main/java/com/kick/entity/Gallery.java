package com.kick.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "gallerys")
@AllArgsConstructor
@Builder
public class Gallery {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="gallery_id")
	private Long id; 
	 
	private String title;
	
	private String content;  

	private String origName;
	
	private String filePath;
	
	private String contentType;
	
	private Long fileSize;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="categories_id")
	private Category categories;
	
	@Column
	private int views;
	
	@ColumnDefault("0")
	private Long commentCnt;
	
	@ColumnDefault("0")
	private Long imageCnt;
	 
	//조회수 증가 
	public void updateViews() {
		this.views++; 
	}

//	public Gallery(String title, String content,User user, String origName, int views) {
//		this.title 		= title;
//		this.content 	= content;
//		this.user		= user;
//		this.origName   = origName;
//		this.views 		= views;
//	}
//
//	public Gallery(String title, String content, User user, int views) {
//		this.title 		= title;
//		this.content 	= content;
//		this.user		= user;
//		this.views 		= views;
//	}
	
	public void update(String title, String content) {
		this.title		= title;
		this.content	= content;
	}
}
