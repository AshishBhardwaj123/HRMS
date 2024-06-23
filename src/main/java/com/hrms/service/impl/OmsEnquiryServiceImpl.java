package com.hrms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.dto.OmsEnquiryDTO;
import com.hrms.entity.OmsEnquiry;
import com.hrms.repo.OmsEnquiryRepository;
import com.hrms.service.OmsEnquiryService;

@Service
public class OmsEnquiryServiceImpl implements OmsEnquiryService {

	@Autowired
	private OmsEnquiryRepository repo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public OmsEnquiryDTO addEnquiry(OmsEnquiryDTO enquiry) {
		OmsEnquiry oms = this.mapper.map(enquiry, OmsEnquiry.class);
		OmsEnquiry saved = this.repo.save(oms);
		OmsEnquiryDTO mapped = this.mapper.map(saved, OmsEnquiryDTO.class);
		return mapped;
	}

	@Override
	public List<OmsEnquiryDTO> getAllEnquiry() {
		List<OmsEnquiry> allEnquiry = this.repo.findAll();
		List<OmsEnquiryDTO> collected = allEnquiry.stream().map(OmsEnquiry -> {
			OmsEnquiryDTO map = this.mapper.map(OmsEnquiry, OmsEnquiryDTO.class);
			return map;
		}).collect(Collectors.toList());
		return collected;
	}

}
