package com.hrms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.dto.BranchDTO;
import com.hrms.dto.BranchListDTO;
import com.hrms.entity.Branch;
import com.hrms.exception.ResourceNotFoundException;
import com.hrms.repo.BranchRepository;
import com.hrms.service.BranchService;

@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchRepository repo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public BranchDTO addNewBranch(BranchDTO dto) {
		dto.setBranchName(dto.getBranchName().toUpperCase());
		Branch branch = this.mapper.map(dto, Branch.class);
		Branch savedBranch = this.repo.save(branch);
		BranchDTO map = this.mapper.map(savedBranch, BranchDTO.class);
		return map;
	}

	@Override
	public List<BranchDTO> getAllBranch() {
		List<Branch> allBranch = this.repo.findAll();
		List<BranchDTO> list = allBranch.stream().map(branch -> {
			BranchDTO dto = this.mapper.map(branch, BranchDTO.class);
			return dto;
		}).collect(Collectors.toList());
		return list;
	}

	@Override
	public BranchDTO getBranchDetails(Long id) {
		Branch branch = this.repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Branch", "id", id));
		BranchDTO branchDTO = this.mapper.map(branch, BranchDTO.class);
		return branchDTO;
	}

	@Override
	public List<BranchListDTO> getBranchName() {
		List<Branch> list = this.repo.findAll();
		List<BranchListDTO> dtoList = list.stream().map(branch -> {
			BranchListDTO dto = this.mapper.map(branch, BranchListDTO.class);
			return dto;
		}).collect(Collectors.toList());
		return dtoList;
	}

}
