package com.kick.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kick.Coupang;
import com.kick.CoupangItems;
import com.kick.dto.CoupangDto;
import com.kick.entity.CoupangImagesRepository;
import com.kick.entity.CoupangItemsRepository;
import com.kick.entity.CoupangRepository;

@Transactional
@Service
public class CoupangService {
	
	@Autowired CoupangRepository coupangRepository; 
	@Autowired CoupangItemsRepository coupangItemsRepository;
	@Autowired CoupangImagesRepository coupangImagesRepository;
	
	@Transactional(readOnly = true)
	public CoupangDto coupangList2() {
//		List<CoupangItems> items = coupangItemsRepository.findAll((long) 466);
		Optional<Coupang> result= coupangRepository.findAllList((long) 432);
		return new CoupangDto(result);
		
	} 
	 
}
