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

import com.hrms.dto.DepartmentDTO;
import com.hrms.dto.DepartmentListDTO;
import com.hrms.service.DepartmentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/hr/department")
public class DepartmentController {

	@Autowired
	private DepartmentService service;

//	add department
	@PostMapping("/add")
	public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody DepartmentDTO departmentDto) {
		DepartmentDTO createDepartmant = this.service.createDepartmant(departmentDto);
		return new ResponseEntity<>(createDepartmant, HttpStatus.CREATED);
	}

//	get all department
	@GetMapping("/getAll")
	public ResponseEntity<List<DepartmentDTO>> getAllDepartment() {
		List<DepartmentDTO> allDepartment = this.service.getAllDepartment();
		return new ResponseEntity<>(allDepartment, HttpStatus.OK);
	}

//	get single department
	@GetMapping("/getDepartment/{id}")
	public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable Long id) {
		DepartmentDTO department = this.service.getDepartment(id);
		return new ResponseEntity<>(department, HttpStatus.OK);
	}

//	get department list name
	@GetMapping("/getAllDepartmentName")
	public ResponseEntity<List<DepartmentListDTO>> getAllDepartmentName() {
		List<DepartmentListDTO> allDepartment = this.service.getAllDepartmentName();
		return new ResponseEntity<>(allDepartment, HttpStatus.OK);
	}

}
