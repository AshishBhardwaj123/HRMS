package com.hrms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.dto.DepartmentDTO;
import com.hrms.dto.DepartmentListDTO;
import com.hrms.entity.Department;
import com.hrms.exception.ResourceNotFoundException;
import com.hrms.repo.DepartmentRepository;
import com.hrms.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public DepartmentDTO createDepartmant(DepartmentDTO departmentDto) {
		Department department = this.mapper.map(departmentDto, Department.class);
		Department savedDepartment = this.departmentRepo.save(department);
		DepartmentDTO mapedDepartment = this.mapper.map(savedDepartment, DepartmentDTO.class);
		return mapedDepartment;
	}

	@Override
	public List<DepartmentDTO> getAllDepartment() {
		List<Department> allDepartments = this.departmentRepo.findAll();
		List<DepartmentDTO> collectedList = allDepartments.stream().map(department -> {
			DepartmentDTO mappedDto = this.mapper.map(department, DepartmentDTO.class);
			return mappedDto;
		}).collect(Collectors.toList());
		return collectedList;
	}

	@Override
	public DepartmentDTO getDepartment(Long id) {
		Department department = this.departmentRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
		DepartmentDTO mappedDto = this.mapper.map(department, DepartmentDTO.class);
		return mappedDto;
	}

	@Override
	public List<DepartmentListDTO> getAllDepartmentName() {
		List<Department> findAll = this.departmentRepo.findAll();
		List<DepartmentListDTO> collectedList = findAll.stream().map(department -> {
			DepartmentListDTO mappedDto = this.mapper.map(department, DepartmentListDTO.class);
			return mappedDto;
		}).collect(Collectors.toList());
		return collectedList;
	}

}
