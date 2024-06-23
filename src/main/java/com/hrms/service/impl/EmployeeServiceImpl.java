package com.hrms.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hrms.dto.EmployeeDTO;
import com.hrms.dto.EmployeeForLeaveDTO;
import com.hrms.dto.EmployeeProjectDTO;
import com.hrms.entity.Branch;
import com.hrms.entity.Department;
import com.hrms.entity.Employee;
import com.hrms.enums.RoleStatus;
import com.hrms.exception.ResourceNotFoundException;
import com.hrms.repo.BranchRepository;
import com.hrms.repo.DepartmentRepository;
import com.hrms.repo.EmployeeRepository;
import com.hrms.service.EmployeeService;
import com.hrms.utils.FileUploadUtilsMethods;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repo;

	@Autowired
	private DepartmentRepository departmentRepo;

	@Autowired
	private BranchRepository branchRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public EmployeeDTO createEmployee(MultipartFile profile, MultipartFile appoinmentLetter,
			MultipartFile relivingLetter, MultipartFile experienceLetter, EmployeeDTO dto) throws IOException {

		if (!profile.isEmpty()) {
			String key = FileUploadUtilsMethods.generateKey(profile.getOriginalFilename());
			String profilePath = FileUploadUtilsMethods
					.uploadFileToS3(FileUploadUtilsMethods.convertMultipartFileToFile(profile), key);
			dto.setProfilePath(profilePath);
		}

		if (!appoinmentLetter.isEmpty()) {
			String key = FileUploadUtilsMethods.generateKey(appoinmentLetter.getOriginalFilename());
			String appointmentLetterPath = FileUploadUtilsMethods
					.uploadFileToS3(FileUploadUtilsMethods.convertMultipartFileToFile(appoinmentLetter), key);
			dto.setAppoinmentLetterPath(appointmentLetterPath);
		}

		if (!relivingLetter.isEmpty()) {
			System.out.println("reliving letter");
			String key = FileUploadUtilsMethods.generateKey(relivingLetter.getOriginalFilename());
			String relievingLetterPath = FileUploadUtilsMethods
					.uploadFileToS3(FileUploadUtilsMethods.convertMultipartFileToFile(relivingLetter), key);
			dto.setRelivingLetterPath(relievingLetterPath);
		}

		if (!experienceLetter.isEmpty()) {
			System.out.println("experience letter");
			String key = FileUploadUtilsMethods.generateKey(experienceLetter.getOriginalFilename());
			String experienceLetterPath = FileUploadUtilsMethods
					.uploadFileToS3(FileUploadUtilsMethods.convertMultipartFileToFile(experienceLetter), key);
			dto.setExperienceLetterPath(experienceLetterPath);
		}

		Employee mapedEmployee = this.mapper.map(dto, Employee.class);
		mapedEmployee
				.setPassword(this.encoder.encode(mapedEmployee.getEmployeeId() + "@" + mapedEmployee.getFirstName()));
		mapedEmployee.setEmployeeRole(RoleStatus.ROLE_EMPLOYEE);
		Employee savedEmp = this.repo.save(mapedEmployee);
		EmployeeDTO map = this.mapper.map(savedEmp, EmployeeDTO.class);

		return map;
	}

	@Override
	public EmployeeDTO addHR(MultipartFile profile, MultipartFile appoinmentLetter, MultipartFile relivingLetter,
			MultipartFile experienceLetter, EmployeeDTO dto) throws IOException {
		if (!profile.isEmpty()) {
			String key = FileUploadUtilsMethods.generateKey(profile.getOriginalFilename());
			String profilePath = FileUploadUtilsMethods
					.uploadFileToS3(FileUploadUtilsMethods.convertMultipartFileToFile(profile), key);
			dto.setProfilePath(profilePath);
		}

		if (!appoinmentLetter.isEmpty()) {
			String key = FileUploadUtilsMethods.generateKey(appoinmentLetter.getOriginalFilename());
			String appointmentLetterPath = FileUploadUtilsMethods
					.uploadFileToS3(FileUploadUtilsMethods.convertMultipartFileToFile(appoinmentLetter), key);
			dto.setAppoinmentLetterPath(appointmentLetterPath);
		}

		if (!relivingLetter.isEmpty()) {
			System.out.println("reliving letter");
			String key = FileUploadUtilsMethods.generateKey(relivingLetter.getOriginalFilename());
			String relievingLetterPath = FileUploadUtilsMethods
					.uploadFileToS3(FileUploadUtilsMethods.convertMultipartFileToFile(relivingLetter), key);
			dto.setRelivingLetterPath(relievingLetterPath);
		}

		if (!experienceLetter.isEmpty()) {
			System.out.println("experience letter");
			String key = FileUploadUtilsMethods.generateKey(experienceLetter.getOriginalFilename());
			String experienceLetterPath = FileUploadUtilsMethods
					.uploadFileToS3(FileUploadUtilsMethods.convertMultipartFileToFile(experienceLetter), key);
			dto.setExperienceLetterPath(experienceLetterPath);
		}

		Employee mapedEmployee = this.mapper.map(dto, Employee.class);
		mapedEmployee
				.setPassword(this.encoder.encode(mapedEmployee.getEmployeeId() + "@" + mapedEmployee.getFirstName()));
		mapedEmployee.setEmployeeRole(RoleStatus.ROLE_MANAGEMENT);
		Employee savedEmp = this.repo.save(mapedEmployee);
		EmployeeDTO map = this.mapper.map(savedEmp, EmployeeDTO.class);

		return map;
	}

	@Override
	public EmployeeDTO updateEmployee(EmployeeDTO emp, Long id) {
		return null;
	}

	@Override
	public EmployeeDTO getEmployee(Long id) {
		Employee employee = this.repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
		EmployeeDTO mapDto = this.mapper.map(employee, EmployeeDTO.class);
		return mapDto;
	}

	@Override
	public List<EmployeeDTO> getAllEmployee() {
		List<Employee> allEmployee = this.repo.findAll();
		List<EmployeeDTO> collectedList = allEmployee.stream().map(emp -> {
			EmployeeDTO mapDto = this.mapper.map(emp, EmployeeDTO.class);
			return mapDto;
		}).collect(Collectors.toList());
		return collectedList;
	}

	@Override
	public EmployeeDTO getEmployeeByEmpId(String empId) {
		Employee employee = this.repo.findByEmployeeId(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", empId));
		EmployeeDTO dto = this.mapper.map(employee, EmployeeDTO.class);
		return dto;
	}

	@Override
	public EmployeeProjectDTO getSingleEmployeeWithProject(Long id) {
		Employee employee = this.repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
		EmployeeProjectDTO mapped = this.mapper.map(employee, EmployeeProjectDTO.class);
		return mapped;
	}

	@Override
	public List<EmployeeForLeaveDTO> getAllEmployeeDetailsForLeave() {
		List<Employee> findAllEmp = this.repo.findAll();
		List<EmployeeForLeaveDTO> collectted = findAllEmp.stream().map(empLeave -> {
			EmployeeForLeaveDTO mapped = this.mapper.map(empLeave, EmployeeForLeaveDTO.class);
			return mapped;
		}).collect(Collectors.toList());
		return collectted;
	}

	@Override
	public EmployeeForLeaveDTO getEmployeeDetailsForLeave(Long id) {
		Employee employee = this.repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
		EmployeeForLeaveDTO mapped = this.mapper.map(employee, EmployeeForLeaveDTO.class);
		return mapped;
	}

	@Override
	public EmployeeDTO updatePassword(Long id, String password) {
		Employee emp = this.repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
		emp.setPassword(password);
		this.repo.save(emp);
		EmployeeDTO mapped = this.mapper.map(emp, EmployeeDTO.class);
		return mapped;
	}

	@Override
	public List<EmployeeDTO> getAllEmployeeWithBranch(String empId) {
		Employee employee = this.repo.findByEmployeeId(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", empId));
		String branchName = employee.getBranch().getBranchName();
		List<Employee> list = this.repo.findEmployeesByBranchName(branchName);
		List<EmployeeDTO> collectted = list.stream().map(empLeave -> {
			EmployeeDTO mapped = this.mapper.map(empLeave, EmployeeDTO.class);
			return mapped;
		}).collect(Collectors.toList());
		return collectted;
	}

	@Override
	public List<EmployeeDTO> getEmployeeByDepartmentandBranch(Long departmentId, Long branchId) {
		Branch branch = this.branchRepo.findById(branchId)
				.orElseThrow(() -> new ResourceNotFoundException("Branch", "Id", branchId));
		Department department = this.departmentRepo.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "Id", departmentId));
		List<Employee> findByDepartmentAndBranch = this.repo.findByDepartmentAndBranch(department, branch);
		List<EmployeeDTO> collect = findByDepartmentAndBranch.stream().map(employee -> {
			EmployeeDTO map = this.mapper.map(employee, EmployeeDTO.class);
			return map;
		}).collect(Collectors.toList());
		return collect;
	}
}
