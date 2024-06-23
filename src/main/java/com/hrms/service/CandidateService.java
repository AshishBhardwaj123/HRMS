package com.hrms.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hrms.dto.CandidateDTO;

public interface CandidateService {
	CandidateDTO newCandidate(MultipartFile resume, CandidateDTO dto) throws IOException;
	
	CandidateDTO getCandidate(Long id);

	List<CandidateDTO> getAllCandidate();
	
	List<CandidateDTO> getAllCandidateInDescOrder();
	
	CandidateDTO selectCandidate(Long id);
	
	CandidateDTO rejectCandidate(Long id);
	
	List<CandidateDTO> getCandidateByBranch(String empId);
}
