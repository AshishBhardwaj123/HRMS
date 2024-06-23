package com.hrms.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hrms.dto.LeavesDTO;

public interface EmployeeLeaveService {
	LeavesDTO addLeave(MultipartFile document, LeavesDTO dto) throws IOException;

	List<LeavesDTO> getAllLeave();
	
	List<LeavesDTO> getAllLeaveDesc();
	
	List<LeavesDTO> getAllEmployeeByBranchName(String empId);
	
	List<LeavesDTO> getAllEmployeeManagement();
	
	LeavesDTO getLeave(Long id);
	
	LeavesDTO acceptLeave(Long id, LeavesDTO dto);
	
	LeavesDTO rejectLeave(Long id, LeavesDTO dto);
	
	
}
