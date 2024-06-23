package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.dto.BranchDTO;
import com.hrms.dto.BranchListDTO;
import com.hrms.service.BranchService;

@CrossOrigin("*")
@RestController
@RequestMapping("/branch")
public class BranchController {

	@Autowired
	private BranchService service;

//	add new branch
	@PostMapping("/add")
	public ResponseEntity<BranchDTO> addNewBranch(@RequestBody BranchDTO dto) {
		BranchDTO newBranch = this.service.addNewBranch(dto);
		return new ResponseEntity<BranchDTO>(newBranch, HttpStatus.CREATED);
	}

//	get All branch
	@GetMapping("/getAll")
	public ResponseEntity<List<BranchDTO>> getAllBranch() {
		List<BranchDTO> allBranch = this.service.getAllBranch();
		return new ResponseEntity<List<BranchDTO>>(allBranch, HttpStatus.OK);
	}
	
//	get single branch
	@GetMapping("/get/{id}")
	public ResponseEntity<BranchDTO> getBranch(@PathVariable Long id) {
		BranchDTO branchDetails = this.service.getBranchDetails(id);
		return new ResponseEntity<BranchDTO>(branchDetails, HttpStatus.OK);
	}
	
//	get all branch name
	@GetMapping("/getAllBranchName")
	public ResponseEntity<List<BranchListDTO>> getAllBranchName() {
		List<BranchListDTO> allBranch = this.service.getBranchName();
		return new ResponseEntity<List<BranchListDTO>>(allBranch, HttpStatus.OK);
	}	

}
