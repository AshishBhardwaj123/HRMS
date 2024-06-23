package com.hrms.service;

import java.util.List;

import com.hrms.dto.BranchDTO;
import com.hrms.dto.BranchListDTO;

public interface BranchService {
	BranchDTO addNewBranch(BranchDTO dto);

	List<BranchDTO> getAllBranch();

	BranchDTO getBranchDetails(Long id);
	
	List<BranchListDTO> getBranchName();
}
