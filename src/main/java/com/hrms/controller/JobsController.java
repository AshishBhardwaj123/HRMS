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

import com.hrms.dto.JobsDTO;
import com.hrms.service.JobsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/hr/jobs")
public class JobsController {
	@Autowired
	private JobsService service;

//	add new job
	@PostMapping("/add")
	public ResponseEntity<JobsDTO> addJob(@RequestBody JobsDTO jobsDto) {
		JobsDTO add = this.service.add(jobsDto);
		return new ResponseEntity<>(add, HttpStatus.CREATED);
	}

//	get all job details
	@GetMapping("/getAllJobs")
	public ResponseEntity<List<JobsDTO>> getAllJob() {
		List<JobsDTO> allJobs = this.service.allJobs();
		return new ResponseEntity<>(allJobs, HttpStatus.OK);
	}

//	get single job details with id
	@GetMapping("/getJob/{id}")
	public ResponseEntity<JobsDTO> getJob(@PathVariable Long id) {
		JobsDTO job = this.service.getJob(id);
		return new ResponseEntity<>(job, HttpStatus.OK);
	}

//	get job details by branch
	@GetMapping("/getJobByBranch/{empId}")
	public ResponseEntity<List<JobsDTO>> getJobsByBranch(@PathVariable String empId) {
		List<JobsDTO> jobsByBranch = this.service.getJobsByBranch(empId);
		return new ResponseEntity<List<JobsDTO>>(jobsByBranch, HttpStatus.OK);
	}

}
