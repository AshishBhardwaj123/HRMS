package com.hrms.dto;

import java.time.LocalDate;

import com.hrms.entity.Employee;
import com.hrms.entity.Projects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeProjectReportDTO {
	private Long id;
	private String reportTitle;
	private String reportDetails;
	private LocalDate reportDate;

	private Employee employee;
	private Projects project;
}
