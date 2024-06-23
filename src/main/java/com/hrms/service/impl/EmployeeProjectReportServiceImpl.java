package com.hrms.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.dto.EmployeeProjectReportDTO;
import com.hrms.entity.EmployeeProjectReport;
import com.hrms.exception.ResourceNotFoundException;
import com.hrms.repo.EmployeeProjectReportRepository;
import com.hrms.service.EmployeeProjectReportService;

@Service
public class EmployeeProjectReportServiceImpl implements EmployeeProjectReportService {

	@Autowired
	private EmployeeProjectReportRepository repo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public EmployeeProjectReportDTO addNewReport(EmployeeProjectReportDTO employeeProjectReportDTO) {
		EmployeeProjectReport projectReport = this.mapper.map(employeeProjectReportDTO, EmployeeProjectReport.class);
		projectReport.setReportDate(LocalDate.now());
		EmployeeProjectReport employeeProjectReport = this.repo.save(projectReport);
		EmployeeProjectReportDTO dto = this.mapper.map(employeeProjectReport, EmployeeProjectReportDTO.class);
		return dto;
	}

	@Override
	public List<EmployeeProjectReportDTO> getAllReport() {
		List<EmployeeProjectReport> findAll = this.repo.findAll();
		List<EmployeeProjectReportDTO> list = findAll.stream().map(empReport -> {
			EmployeeProjectReportDTO map = this.mapper.map(empReport, EmployeeProjectReportDTO.class);
			return map;
		}).collect(Collectors.toList());
		return list;
	}

	@Override
	public EmployeeProjectReportDTO getSingleReport(Long id) {
		EmployeeProjectReport projectReport = this.repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee or Project", "id", id));
		EmployeeProjectReportDTO reportDTO = this.mapper.map(projectReport, EmployeeProjectReportDTO.class);
		return reportDTO;
	}

}
