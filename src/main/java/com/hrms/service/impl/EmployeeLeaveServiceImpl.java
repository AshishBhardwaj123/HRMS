package com.hrms.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hrms.dto.LeavesDTO;
import com.hrms.entity.Employee;
import com.hrms.entity.Leaves;
import com.hrms.enums.MultiStatus;
import com.hrms.exception.ResourceNotFoundException;
import com.hrms.repo.EmployeeLeaveRepository;
import com.hrms.repo.EmployeeRepository;
import com.hrms.service.EmployeeLeaveService;
import com.hrms.utils.FileUploadUtilsMethods;

@Service
public class EmployeeLeaveServiceImpl implements EmployeeLeaveService {

	@Autowired
	private EmployeeLeaveRepository leaveRepo;

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<LeavesDTO> getAllLeave() {
		List<Leaves> list = this.leaveRepo.findAll();
		List<LeavesDTO> collect = list.stream().map(leave -> {
			LeavesDTO leaveDTO = this.mapper.map(leave, LeavesDTO.class);
			leaveDTO.setDepartmentName(leaveDTO.getEmployee().getDepartment().getDepartmentName());
			return leaveDTO;
		}).collect(Collectors.toList());
		return collect;
	}

	@Override
	public LeavesDTO getLeave(Long id) {
		Leaves leave = this.leaveRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Leave report", "id", id));
		LeavesDTO leavesDTO = this.mapper.map(leave, LeavesDTO.class);
		leavesDTO.setDepartmentName(leavesDTO.getEmployee().getDepartment().getDepartmentName());
		return leavesDTO;
	}

	@Override
	public LeavesDTO addLeave(MultipartFile document, LeavesDTO dto) throws IOException {
		if (!document.isEmpty()) {
			String key = FileUploadUtilsMethods.generateKey(document.getOriginalFilename());
			String profilePath = FileUploadUtilsMethods
					.uploadFileToS3(FileUploadUtilsMethods.convertMultipartFileToFile(document), key);
			dto.setAttachedDocuments(profilePath);
		}

		Leaves employeeLeave = this.mapper.map(dto, Leaves.class);
		employeeLeave.setStatus(MultiStatus.PENDING);
		employeeLeave.setAppliedDate(LocalDate.now());
		Leaves saved = this.leaveRepo.save(employeeLeave);
		LeavesDTO mapped = this.mapper.map(saved, LeavesDTO.class);
		return mapped;
	}

	@Override
	public LeavesDTO acceptLeave(Long id, LeavesDTO dto) {
		Leaves leaves = this.leaveRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Leave report", "id", id));
		leaves.setReply(dto.getReply());
		leaves.setStatus(MultiStatus.ACCEPTED);
		Leaves savedLeave = this.leaveRepo.save(leaves);
		LeavesDTO leavesDTO = this.mapper.map(savedLeave, LeavesDTO.class);
		leavesDTO.setDepartmentName(leaves.getEmployee().getDepartment().getDepartmentName());
		return leavesDTO;
	}

	@Override
	public LeavesDTO rejectLeave(Long id, LeavesDTO dto) {
		Leaves leaves = this.leaveRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Leave report", "id", id));
		leaves.setReply(dto.getReply());
		leaves.setStatus(MultiStatus.REJECTED);
		Leaves savedLeave = this.leaveRepo.save(leaves);
		LeavesDTO leavesDTO = this.mapper.map(savedLeave, LeavesDTO.class);
		leavesDTO.setDepartmentName(leaves.getEmployee().getDepartment().getDepartmentName());
		return leavesDTO;
	}

	@Override
	public List<LeavesDTO> getAllLeaveDesc() {
		List<Leaves> desc = this.leaveRepo.findAllByOrderByLeaveIdDesc();
		List<LeavesDTO> collect = desc.stream().map(leave -> {
			LeavesDTO leaveDTO = this.mapper.map(leave, LeavesDTO.class);
			leaveDTO.setDepartmentName(leaveDTO.getEmployee().getDepartment().getDepartmentName());
			return leaveDTO;
		}).collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<LeavesDTO> getAllEmployeeByBranchName(String empId) {
		Employee employee = this.empRepo.findByEmployeeId(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", empId));
		String branchName = employee.getBranch().getBranchName();
		List<Leaves> list = this.leaveRepo.findLeavesByEmployeeBranchName(branchName);
		List<LeavesDTO> collectted = list.stream().map(empLeave -> {
			LeavesDTO mapped = this.mapper.map(empLeave, LeavesDTO.class);
			mapped.setDepartmentName(mapped.getEmployee().getDepartment().getDepartmentName());
			return mapped;
		}).collect(Collectors.toList());
		return collectted;
	}

	@Override
	public List<LeavesDTO> getAllEmployeeManagement() {
		List<Leaves> managementList = this.leaveRepo.findLeavesByRoleManagement();
		List<LeavesDTO> collectted = managementList.stream().map(empLeave -> {
			LeavesDTO mapped = this.mapper.map(empLeave, LeavesDTO.class);
			mapped.setDepartmentName(mapped.getEmployee().getDepartment().getDepartmentName());
			return mapped;
		}).collect(Collectors.toList());
		return collectted;
	}
}
