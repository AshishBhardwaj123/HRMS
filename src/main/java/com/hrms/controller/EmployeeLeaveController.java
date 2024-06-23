package com.hrms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hrms.dto.LeavesDTO;
import com.hrms.service.EmployeeLeaveService;

@CrossOrigin("*")
@RestController
@RequestMapping("/hr/employee")
public class EmployeeLeaveController {

	@Autowired
	private EmployeeLeaveService leaveService;

//	post new leave application
	@PostMapping("/addLeave")
	public ResponseEntity<LeavesDTO> addLeave(@RequestParam("documents") MultipartFile document,
			@RequestParam("leaveJson") String leaveJson) throws IOException {
		ObjectMapper obj = new ObjectMapper();
		obj.registerModule(new JavaTimeModule());
		obj.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		LeavesDTO employeeLeave = obj.readValue(leaveJson, LeavesDTO.class);
		LeavesDTO addLeave = this.leaveService.addLeave(document, employeeLeave);
		return new ResponseEntity<LeavesDTO>(addLeave, HttpStatus.CREATED);
	}

//	get all leave details
	@GetMapping("/getAllLeave")
	public ResponseEntity<List<LeavesDTO>> getAllLeave() {
		List<LeavesDTO> allLeave = this.leaveService.getAllLeave();
		return new ResponseEntity<List<LeavesDTO>>(allLeave, HttpStatus.OK);
	}

//	get single leave details
	@GetMapping("/getLeave/{id}")
	public ResponseEntity<LeavesDTO> getLeave(@PathVariable Long id) {
		LeavesDTO leaveWithDetails = this.leaveService.getLeave(id);
		return new ResponseEntity<LeavesDTO>(leaveWithDetails, HttpStatus.OK);
	}

//	all leave details in descending order
	@GetMapping("/getAllLeaveDesc")
	public ResponseEntity<List<LeavesDTO>> getAllLeaveDesc() {
		List<LeavesDTO> allLeaveDesc = this.leaveService.getAllLeaveDesc();
		return new ResponseEntity<List<LeavesDTO>>(allLeaveDesc, HttpStatus.OK);
	}

//	accept leave application
	@PutMapping("/acceptLeave/{id}")
	public ResponseEntity<LeavesDTO> acceptLeaveApplication(@PathVariable Long id, @RequestBody LeavesDTO dto) {
		LeavesDTO acceptLeave = this.leaveService.acceptLeave(id, dto);
		return new ResponseEntity<LeavesDTO>(acceptLeave, HttpStatus.OK);
	}

//	reject leave application
	@PutMapping("/rejectLeave/{id}")
	public ResponseEntity<LeavesDTO> rejectLeaveApplication(@PathVariable Long id, @RequestBody LeavesDTO dto) {
		LeavesDTO rejectLeave = this.leaveService.rejectLeave(id, dto);
		return new ResponseEntity<LeavesDTO>(rejectLeave, HttpStatus.OK);
	}

//	get all leave with branch in descendingOrder
	@GetMapping("/getLeaveByBranch/{empId}")
	public ResponseEntity<List<LeavesDTO>> getLeaveByBranchDesc(@PathVariable String empId) {
		List<LeavesDTO> allEmployeeByBranchName = this.leaveService.getAllEmployeeByBranchName(empId);
		return new ResponseEntity<List<LeavesDTO>>(allEmployeeByBranchName, HttpStatus.OK);
	}

//	get leave of management team
	@GetMapping("/getLeaveOfManagement")
	public ResponseEntity<List<LeavesDTO>> getLeaveOfManagement() {
		List<LeavesDTO> allEmployeeManagement = this.leaveService.getAllEmployeeManagement();
		return new ResponseEntity<List<LeavesDTO>>(allEmployeeManagement, HttpStatus.OK);
	}

}
