package com.hrms.service;

import java.util.List;

import com.hrms.dto.EmployeeProjectReportDTO;

public interface EmployeeProjectReportService {
	EmployeeProjectReportDTO addNewReport(EmployeeProjectReportDTO employeeProjectReportDTO);
	
	List<EmployeeProjectReportDTO> getAllReport();
	
	EmployeeProjectReportDTO getSingleReport(Long id);
}
