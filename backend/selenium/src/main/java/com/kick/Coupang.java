package com.kick;

import java.util.ArrayList;
import java.util.List;


import org.apache.poi.util.SystemOutLogger;

import com.kick.dto.CoupangDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class Coupang {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="coupang_id")
	private Long id; 
	
	//등록상품명O
	private String sellerProductName;
	//업체코드O
	private String vendorId;
	//판매시작일시O
	private String saleStartedAt;
	//판매종료일시O
	private String saleEndedAt;
	//노출상품명X
	private String displayProductName;
	/*
	SEQUENCIAL	일반배송(순차배송)
	COLD_FRESH	신선냉동
	MAKE_ORDER	주문제작
	AGENT_BUY	구매대행
	VENDOR_DIRECT	설치배송 또는 판매자 직접 전달
	*/
	//O
	private String deliveryMethod;
	//O
	private String deliveryCompanyCode;
	/*
		FREE	무료배송
		NOT_FREE	유료배송
		CHARGE_RECEIVED	착불배송
		CONDITIONAL_FREE	조건부 무료배송 
		● 무료배송 설정 시
		초도반품배송비(편도)[deliveryChargeOnReturn]와, 반품배송비(편도)[returnCharge] 금액 설정
		● 유료배송 설정 시
		기본배송비[deliveryCharge]와 반품배송비(편도) 금액 설정
		● 조건부 무료배송 설정 시
		기본배송비와 반품배송비(편도) 금액 설정
		● 착불배송 설정 시
		착불배송 가능 카테고리는 따로 정리되어 있으며, 판매자 안내를 위해 판매자콜센터에 공유가 되었습니다.
		※ [CONDITIONAL_FREE] 사용 시, 원하는 조건부 무료배송 금액을 별도로 설정할 수 있습니다.
	 */
	//O
	private String deliveryChargeType;
	//O
	private int deliveryCharge;
	//O
	private int freeShipOverAmount;
	//O
	private int deliveryChargeOnReturn;
	/*
	 *  Y	도서산간 배송
	 *	N	도서산간 배송안함
	 * 
	 */
	//O
	private String remoteAreaDeliverable;
	
	/*
	 * UNION_DELIVERY	묶음 배송 가능
	 * NOT_UNION_DELIVERY	묶음 배송 불가능
	 */
	//O
	private String unionDeliveryType;
	//O 
	private String returnCenterCode;
	//O
	private String returnChargeName;
	//O
	private String companyContactNumber;
	//O
	private String returnZipCode;
	//O
	private String returnAddress;
	//O
	private String returnAddressDetail;
	//O
	private int returnCharge;
	//O
	private int outboundShippingPlaceCode;
	
	//업체 ID qudtn0295
	private String vendorUserId;
	//O
	private Boolean requested;
	//O
	@OneToMany(mappedBy="coupang", cascade = CascadeType.ALL)
	private List<CoupangItems> items = new ArrayList<>();
	
	public void items(CoupangItems item) {
		this.items.add(item);
	}

}
