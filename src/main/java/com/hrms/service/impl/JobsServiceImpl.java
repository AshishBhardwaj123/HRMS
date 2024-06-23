package com.hrms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.dto.JobsDTO;
import com.hrms.entity.Employee;
import com.hrms.entity.Jobs;
import com.hrms.exception.ResourceNotFoundException;
import com.hrms.repo.EmployeeRepository;
import com.hrms.repo.JobsRepository;
import com.hrms.service.JobsService;

@Service
public class JobsServiceImpl implements JobsService {

	@Autowired
	private JobsRepository jobRepo;
	
	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public JobsDTO add(JobsDTO jobDto) {
		Jobs jobs = this.mapper.map(jobDto, Jobs.class);
		Jobs saveJob = this.jobRepo.save(jobs);
		JobsDTO mapDto = this.mapper.map(saveJob, JobsDTO.class);
		return mapDto;
	}

	@Override
	public List<JobsDTO> allJobs() {
		List<Jobs> allJobs = this.jobRepo.findAll();
		List<JobsDTO> collectedJobList = allJobs.stream().map(job -> {
			JobsDTO jobDto = this.mapper.map(job, JobsDTO.class);
			return jobDto;
		}).collect(Collectors.toList());
		return collectedJobList;
	}

	@Override
	public JobsDTO getJob(Long id) {
		Jobs jobs = this.jobRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Job", "id", id));
		JobsDTO dto = this.mapper.map(jobs, JobsDTO.class);
		return dto;
	}

	@Override
	public List<JobsDTO> getJobsByBranch(String empId) {
		Employee employee = this.empRepo.findByEmployeeId(empId).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", empId));
		String branchName = employee.getBranch().getBranchName();
		List<Jobs> byBranchName = this.jobRepo.findJobsByBranchName(branchName);
		List<JobsDTO> collectedJobList = byBranchName.stream().map(job -> {
			JobsDTO jobDto = this.mapper.map(job, JobsDTO.class);
			return jobDto;
		}).collect(Collectors.toList());
		return collectedJobList;
	}

}
