package com.hrms.service;

import java.util.List;

import com.hrms.dto.JobsDTO;

public interface JobsService {
	JobsDTO add(JobsDTO jobDto);

	List<JobsDTO> allJobs();

	JobsDTO getJob(Long id); 
	
	List<JobsDTO> getJobsByBranch(String empId);
}

