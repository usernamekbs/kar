package com.kick;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class CoupangAttributes {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id; 
	/*
	옵션타입명
	카테고리 메타 정보 조회 API 또는 전체카테고리 리스트 다운로드 엑셀 파일을 통해, 카테고리에 맞는 옵션타입명 확인 및 선택 가능 (구매옵션은 자유롭게 입력 가능)
	최대 길이 25자 제한
	*/
	private String attributeTypeName;
	
	private String attributeValueName;
	
	private String exposed;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="items_id")
	private CoupangItems coupangItems;
	
}
