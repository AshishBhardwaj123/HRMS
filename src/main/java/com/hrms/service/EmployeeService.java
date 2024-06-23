package com.hrms.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hrms.dto.EmployeeDTO;
import com.hrms.dto.EmployeeForLeaveDTO;
import com.hrms.dto.EmployeeProjectDTO;

public interface EmployeeService {
	EmployeeDTO createEmployee(MultipartFile profile, MultipartFile appoinmentLetter, MultipartFile relivingLetter,
			MultipartFile experienceLetter, EmployeeDTO dto) throws IOException;

	EmployeeDTO addHR(MultipartFile profile, MultipartFile appoinmentLetter, MultipartFile relivingLetter,
			MultipartFile experienceLetter, EmployeeDTO dto) throws IOException;

	EmployeeDTO updateEmployee(EmployeeDTO dto, Long id);

	EmployeeDTO getEmployee(Long id);

	List<EmployeeDTO> getAllEmployee();

	EmployeeDTO getEmployeeByEmpId(String empId);

	EmployeeProjectDTO getSingleEmployeeWithProject(Long id);

	List<EmployeeForLeaveDTO> getAllEmployeeDetailsForLeave();

	EmployeeForLeaveDTO getEmployeeDetailsForLeave(Long id);

	EmployeeDTO updatePassword(Long id, String password);

	List<EmployeeDTO> getAllEmployeeWithBranch(String empId);

	List<EmployeeDTO> getEmployeeByDepartmentandBranch(Long branchId, Long departmentId);
}
