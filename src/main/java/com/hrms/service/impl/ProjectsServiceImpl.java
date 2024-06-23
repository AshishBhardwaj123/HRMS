package com.hrms.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hrms.dto.ProjectsDTO;
import com.hrms.entity.Employee;
import com.hrms.entity.Projects;
import com.hrms.exception.ResourceNotFoundException;
import com.hrms.repo.EmployeeRepository;
import com.hrms.repo.ProjectsRepository;
import com.hrms.service.ProjectsService;
import com.hrms.utils.FileUploadUtilsMethods;

@Service
public class ProjectsServiceImpl implements ProjectsService {

	@Autowired
	private ProjectsRepository projectRepo;

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public ProjectsDTO addProject(MultipartFile file, ProjectsDTO projectDto) throws IOException {
		if (!file.isEmpty()) {
			String key = FileUploadUtilsMethods.generateKey(file.getOriginalFilename());
			String profilePath = FileUploadUtilsMethods
					.uploadFileToS3(FileUploadUtilsMethods.convertMultipartFileToFile(file), key);
			projectDto.setFilePath(profilePath);
		}

		Projects projects = this.mapper.map(projectDto, Projects.class);
		Projects savedProject = this.projectRepo.save(projects);
		ProjectsDTO savedDto = this.mapper.map(savedProject, ProjectsDTO.class);
		return savedDto;
	}

	@Override
	public List<ProjectsDTO> getAllProjectDetails() {
		List<Projects> listProjects = this.projectRepo.findAll();
		List<ProjectsDTO> collectedDto = listProjects.stream().map(project -> {
			ProjectsDTO projectsDTO = this.mapper.map(project, ProjectsDTO.class);
			return projectsDTO;
		}).collect(Collectors.toList());
		return collectedDto;

	}

	@Override
	public ProjectsDTO getProject(Long id) {
		Projects projects = this.projectRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));
		ProjectsDTO projectsDTO = this.mapper.map(projects, ProjectsDTO.class);
		return projectsDTO;
	}

	@Override
	public List<ProjectsDTO> getAllProjectDeatilsDesc() {
		List<Projects> allProjects = this.projectRepo.findAllByOrderByIdDesc();
		List<ProjectsDTO> collectted = allProjects.stream().map(projects -> {
			ProjectsDTO map = this.mapper.map(projects, ProjectsDTO.class);
			return map;
		}).collect(Collectors.toList());
		return collectted;
	}

	@Override
	public List<ProjectsDTO> getAllProjectByBranch(String empId) {
		Employee employee = this.empRepo.findByEmployeeId(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employye", "id", empId));
		String branchName = employee.getBranch().getBranchName();
		List<Projects> findProjectsByEmployeeBranchName = this.projectRepo.findProjectsByBranchName(branchName);
		List<ProjectsDTO> collect = findProjectsByEmployeeBranchName.stream().map(project -> {
			ProjectsDTO map = this.mapper.map(project, ProjectsDTO.class);
			return map;
		}).collect(Collectors.toList());
		return collect;
	}

	@Override
	public ProjectsDTO getProjectByProjectId(String projectId) {
		Projects projects = this.projectRepo.findByProjectId(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project", "id", projectId));
		ProjectsDTO projectsDTO = this.mapper.map(projects, ProjectsDTO.class);
		return projectsDTO;
	}

}
