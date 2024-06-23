package com.hrms.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hrms.dto.CandidateDTO;
import com.hrms.entity.Candidate;
import com.hrms.entity.Employee;
import com.hrms.enums.MultiStatus;
import com.hrms.exception.ResourceNotFoundException;
import com.hrms.repo.CandidateRepository;
import com.hrms.repo.EmployeeRepository;
import com.hrms.service.CandidateService;
import com.hrms.utils.FileUploadUtilsMethods;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	private CandidateRepository candiRepo;

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CandidateDTO newCandidate(MultipartFile resume, CandidateDTO dto) throws IOException {
		if (!resume.isEmpty()) {
			String key = FileUploadUtilsMethods.generateKey(resume.getOriginalFilename());
			String profilePath = FileUploadUtilsMethods
					.uploadFileToS3(FileUploadUtilsMethods.convertMultipartFileToFile(resume), key);
			dto.setCandidateCvPath(profilePath);
		}
		Candidate candidate = this.mapper.map(dto, Candidate.class);
		candidate.setStatus(MultiStatus.PENDING);
		candidate.setAppliedDate(LocalDate.now());
		Candidate savedCandidate = this.candiRepo.save(candidate);
		CandidateDTO mapped = this.mapper.map(savedCandidate, CandidateDTO.class);
		return mapped;
	}

	@Override
	public CandidateDTO getCandidate(Long id) {
		Candidate candidate = this.candiRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Candidate", "id", id));
		CandidateDTO candidateDTO = this.mapper.map(candidate, CandidateDTO.class);
		candidateDTO.setLocation(candidate.getAppliedFor().getBranch().getBranchName());
		return candidateDTO;
	}

	@Override
	public List<CandidateDTO> getAllCandidate() {
		List<Candidate> allCandidate = this.candiRepo.findAll();
		List<CandidateDTO> collectedCandidate = allCandidate.stream().map(candidate -> {
			CandidateDTO mapped = this.mapper.map(candidate, CandidateDTO.class);
			mapped.setLocation(candidate.getAppliedFor().getBranch().getBranchName());
			return mapped;
		}).collect(Collectors.toList());
		return collectedCandidate;
	}

	@Override
	public List<CandidateDTO> getAllCandidateInDescOrder() {
		List<Candidate> desc = this.candiRepo.findAllByOrderByCandidateIdDesc();
		List<CandidateDTO> collecttedList = desc.stream().map(candidate -> {
			CandidateDTO candidateDTO = this.mapper.map(candidate, CandidateDTO.class);
			return candidateDTO;
		}).collect(Collectors.toList());
		return collecttedList;
	}

	@Override
	public CandidateDTO selectCandidate(Long id) {
		Candidate candidate = this.candiRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Candidate", "id", id));
		candidate.setStatus(MultiStatus.ACCEPTED);
		Candidate saved = this.candiRepo.save(candidate);
		CandidateDTO candidateDTO = this.mapper.map(saved, CandidateDTO.class);
		return candidateDTO;
	}

	@Override
	public CandidateDTO rejectCandidate(Long id) {
		Candidate candidate = this.candiRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Candidate", "id", id));
		candidate.setStatus(MultiStatus.REJECTED);
		Candidate saved = this.candiRepo.save(candidate);
		CandidateDTO candidateDTO = this.mapper.map(saved, CandidateDTO.class);
		return candidateDTO;
	}

	@Override
	public List<CandidateDTO> getCandidateByBranch(String empId) {
		Employee employee = this.empRepo.findByEmployeeId(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", empId));
		String branchName = employee.getBranch().getBranchName();
		List<Candidate> findCandidatesByBranchName = this.candiRepo.findCandidatesByBranchName(branchName);
		List<CandidateDTO> collect = findCandidatesByBranchName.stream().map(candidate -> {
			CandidateDTO map = this.mapper.map(candidate, CandidateDTO.class);
			map.setLocation(candidate.getAppliedFor().getBranch().getBranchName());
			return map;
		}).collect(Collectors.toList());
		return collect;
	}
}
