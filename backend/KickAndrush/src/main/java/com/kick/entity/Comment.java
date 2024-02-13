package com.kick.entity;

import java.util.ArrayList;
import java.util.List;

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

import com.kick.common.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "comments")
@AllArgsConstructor
@Builder
public class Comment extends BaseTimeEntity{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="comment_id")
	private Long id;
	
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="post_id")
	private Post post;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Comment parent;
    
    @OneToMany(mappedBy = "parent", orphanRemoval=true)
    @Builder.Default
    private List<Comment> children = new ArrayList<>();
    
    public void update(String content) {
		this.content = content;
	}
}
