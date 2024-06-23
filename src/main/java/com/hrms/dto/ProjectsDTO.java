package com.hrms.dto;

import java.time.LocalDate;
import java.util.List;

import com.hrms.entity.Branch;
import com.hrms.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectsDTO {
	private Long id;
	private String clientName;
	private String companyName;
	private String phoneNumber;
	private String emailId;
	private String countryName;
	private boolean projectStatus;

	private String projectTitle;
	private String projectId;
	private String projectManager;
	private int teamSize;
	private String usedFor;
	private LocalDate startDate;
	private LocalDate endDate;

	private String description;
	private String technologyUsed;
	private String skillsRequired;

	private String filePath;
	
	private List<Employee> employee;
	
	private Branch branch;
}
