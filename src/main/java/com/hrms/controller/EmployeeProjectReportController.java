package com.hrms.controller;

import java.time.LocalDate;
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

import com.hrms.dto.EmployeeProjectReportDTO;
import com.hrms.service.EmployeeProjectReportService;

@CrossOrigin("*")
@RestController
@RequestMapping("/hr/report")
public class EmployeeProjectReportController {

	@Autowired
	private EmployeeProjectReportService projectService;

//	add new report
	@PostMapping("/add")
	public ResponseEntity<EmployeeProjectReportDTO> addNewReport(@RequestBody EmployeeProjectReportDTO dto) {
		EmployeeProjectReportDTO addNewReport = this.projectService.addNewReport(dto);
		return new ResponseEntity<EmployeeProjectReportDTO>(addNewReport, HttpStatus.CREATED);
	}

//	get all report
	@GetMapping("/getAll")
	public ResponseEntity<List<EmployeeProjectReportDTO>> getAllReport() {
		List<EmployeeProjectReportDTO> allReport = this.projectService.getAllReport();
		return new ResponseEntity<List<EmployeeProjectReportDTO>>(allReport, HttpStatus.OK);
	}
	
//	get single report
	@GetMapping("/get/{id}")
	public ResponseEntity<EmployeeProjectReportDTO> getSingleReport(@PathVariable Long id) {
		EmployeeProjectReportDTO report = this.projectService.getSingleReport(id);
		return new ResponseEntity<EmployeeProjectReportDTO>(report, HttpStatus.OK);
	}
}
