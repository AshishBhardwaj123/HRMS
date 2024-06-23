package com.hrms.dto;

import java.util.List;

import com.hrms.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {
	private Long id;
	private String branchName;
	
	List<Employee> employee;
}
