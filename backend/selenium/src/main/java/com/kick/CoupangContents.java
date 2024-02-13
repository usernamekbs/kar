package com.kick;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class CoupangContents {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="coupang_contents_id")
	private Long id; 
	//O
	private String contentsType;
	//O
	@OneToMany(mappedBy="coupangContents", cascade = CascadeType.ALL)
	private List<CoupangDetails> contentDetails = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="items_id")
	private CoupangItems coupangItems;
	
	public void contentDetails(CoupangDetails coupangDetails) {
		this.contentDetails.add(coupangDetails);
	}
}
