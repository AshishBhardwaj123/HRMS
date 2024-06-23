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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hrms.dto.EmployeeDTO;
import com.hrms.dto.EmployeeForLeaveDTO;
import com.hrms.dto.EmployeeProjectDTO;
import com.hrms.service.EmployeeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/hr/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

//	add new employee
	@PostMapping("/add")
	public ResponseEntity<EmployeeDTO> addEmployee(@RequestParam("profile") MultipartFile profile,
			@RequestParam("appoinment") MultipartFile appoinmentLetter,
			@RequestParam("reliving") MultipartFile relivingLetter,
			@RequestParam("experience") MultipartFile excerienceLetter, @RequestParam("employee") String dto)
			throws IOException {
		ObjectMapper obj = new ObjectMapper();
		obj.registerModule(new JavaTimeModule());
		obj.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		EmployeeDTO emp = obj.readValue(dto, EmployeeDTO.class);
		EmployeeDTO createEmployee = this.service.createEmployee(profile, appoinmentLetter, relivingLetter,
				excerienceLetter, emp);
		return new ResponseEntity<EmployeeDTO>(createEmployee, HttpStatus.CREATED);
	}

//	add new HR
	@PostMapping("/addHR")
	public ResponseEntity<EmployeeDTO> addHR(@RequestParam("profile") MultipartFile profile,
			@RequestParam("appoinment") MultipartFile appoinmentLetter,
			@RequestParam("reliving") MultipartFile relivingLetter,
			@RequestParam("experience") MultipartFile excerienceLetter, @RequestParam("employee") String dto)
			throws IOException {
		ObjectMapper obj = new ObjectMapper();
		obj.registerModule(new JavaTimeModule());
		obj.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		EmployeeDTO emp = obj.readValue(dto, EmployeeDTO.class);
		EmployeeDTO createEmployee = this.service.addHR(profile, appoinmentLetter, relivingLetter, excerienceLetter,
				emp);
		return new ResponseEntity<EmployeeDTO>(createEmployee, HttpStatus.CREATED);
	}

//	get single employee details
	@GetMapping("/getEmp/{id}")
	public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id) {
		EmployeeDTO employee = this.service.getEmployee(id);
		return new ResponseEntity<EmployeeDTO>(employee, HttpStatus.OK);
	}

//	get all employee details
	@GetMapping("/getAllEmp")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
		List<EmployeeDTO> allEmployee = this.service.getAllEmployee();
		return new ResponseEntity<List<EmployeeDTO>>(allEmployee, HttpStatus.OK);
	}

//	get employee details by employee id
	@GetMapping("/getEmployeeByEmpId/{empId}")
	public ResponseEntity<EmployeeDTO> getEmployeeByEmpId(@PathVariable String empId) {
		EmployeeDTO employee = this.service.getEmployeeByEmpId(empId);
		return new ResponseEntity<EmployeeDTO>(employee, HttpStatus.OK);
	}

//	get employee details with his project details
	@GetMapping("/getEmployeeWithProject/{id}")
	public ResponseEntity<EmployeeProjectDTO> getEmployeeWithProjectByEmpId(@PathVariable Long id) {
		EmployeeProjectDTO singleEmployeeWithProject = this.service.getSingleEmployeeWithProject(id);
		return new ResponseEntity<EmployeeProjectDTO>(singleEmployeeWithProject, HttpStatus.OK);
	}

//	get all employee details with his leave details
	@GetMapping("/getAllEmployeeWithLeave")
	public ResponseEntity<List<EmployeeForLeaveDTO>> getAllEmployeeWithLeave() {
		List<EmployeeForLeaveDTO> allEmployeeDetailsForLeave = this.service.getAllEmployeeDetailsForLeave();
		return new ResponseEntity<List<EmployeeForLeaveDTO>>(allEmployeeDetailsForLeave, HttpStatus.OK);
	}

//	get single employee details with his leave details
	@GetMapping("/getEmployeeWithLeave/{id}")
	public ResponseEntity<EmployeeForLeaveDTO> getEmployeeWithLeave(@PathVariable Long id) {
		EmployeeForLeaveDTO employeeDetailsForLeave = this.service.getEmployeeDetailsForLeave(id);
		return new ResponseEntity<EmployeeForLeaveDTO>(employeeDetailsForLeave, HttpStatus.OK);
	}

//	get all employee details with branch
	@GetMapping("/getEmployeeWithBranch/{empId}")
	public ResponseEntity<List<EmployeeDTO>> getEmployeeWithBranch(@PathVariable String empId) {
		List<EmployeeDTO> allEmployeeWithBranch = this.service.getAllEmployeeWithBranch(empId);
		return new ResponseEntity<List<EmployeeDTO>>(allEmployeeWithBranch, HttpStatus.OK);
	}

//	change password
	@PutMapping("/updatePassword/{id}")
	public ResponseEntity<EmployeeDTO> updatePassword(@PathVariable Long id,
			@RequestParam("password") String password) {
		EmployeeDTO updatePassword = this.service.updatePassword(id, password);
		return new ResponseEntity<EmployeeDTO>(updatePassword, HttpStatus.OK);
	}

//	get employee by department & branch
	@GetMapping("/getEmployee/branch/{branchId}/department/{departmentId}")
	public ResponseEntity<List<EmployeeDTO>> getEmployeeByDepartmentandBranch(@PathVariable Long branchId,
			@PathVariable Long departmentId) {
		List<EmployeeDTO> employeeByDepartmentandBranch = this.service.getEmployeeByDepartmentandBranch(departmentId,
				branchId);
		return new ResponseEntity<List<EmployeeDTO>>(employeeByDepartmentandBranch, HttpStatus.OK);
	}
}
