package com.hrms.service;

import java.util.List;

import com.hrms.dto.DepartmentDTO;
import com.hrms.dto.DepartmentListDTO;

public interface DepartmentService {
	DepartmentDTO createDepartmant(DepartmentDTO departmentDto);

	List<DepartmentDTO> getAllDepartment();

	DepartmentDTO getDepartment(Long id);

	List<DepartmentListDTO> getAllDepartmentName();
}
