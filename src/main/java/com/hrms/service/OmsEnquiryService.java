package com.hrms.service;

import java.util.List;

import com.hrms.dto.OmsEnquiryDTO;

public interface OmsEnquiryService {
	OmsEnquiryDTO addEnquiry(OmsEnquiryDTO enquiry);

	List<OmsEnquiryDTO> getAllEnquiry();
}
