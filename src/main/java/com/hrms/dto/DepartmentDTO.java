package com.hrms.dto;

import java.util.List;

import com.hrms.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
	private Long id;
	private String departmentName;
	
	List<Employee> employee;
}
