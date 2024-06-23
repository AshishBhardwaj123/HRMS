package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.dto.OmsEnquiryDTO;
import com.hrms.service.OmsEnquiryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/oms")
public class OmsEnquiryController {

	@Autowired
	private OmsEnquiryService service;

	@PostMapping("/enquiry/add")
	public ResponseEntity<OmsEnquiryDTO> addEnquiry(@RequestBody OmsEnquiryDTO dto) {
		OmsEnquiryDTO addEnquiry = this.service.addEnquiry(dto);
		return new ResponseEntity<OmsEnquiryDTO>(addEnquiry, HttpStatus.CREATED);
	}

	@GetMapping("/enquiry/getAll")
	public ResponseEntity<List<OmsEnquiryDTO>> getAllEnquiry() {
		List<OmsEnquiryDTO> allEnquiry = this.service.getAllEnquiry();
		return new ResponseEntity<List<OmsEnquiryDTO>>(allEnquiry, HttpStatus.OK);
	}
}
