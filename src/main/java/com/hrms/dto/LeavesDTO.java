package com.hrms.dto;

import java.time.LocalDate;

import com.hrms.entity.Employee;
import com.hrms.enums.MultiStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeavesDTO {
	private Long leaveId;
	private String subject;
	private String leaveDetails;
	private String reply;
	private String attachedDocuments;
	private LocalDate appliedDate;
	private LocalDate startDate;
	private LocalDate endDate;
	private MultiStatus status;
	
	private String departmentName;
	
	private Employee employee;
	
}
