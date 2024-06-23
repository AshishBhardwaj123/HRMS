package com.hrms.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hrms.dto.ProjectsDTO;

public interface ProjectsService {
	ProjectsDTO addProject(MultipartFile file, ProjectsDTO projectDto) throws IOException;

	List<ProjectsDTO> getAllProjectDetails();

	ProjectsDTO getProject(Long id);
	
	ProjectsDTO getProjectByProjectId(String projectId);

	List<ProjectsDTO> getAllProjectDeatilsDesc();
	
	List<ProjectsDTO> getAllProjectByBranch(String empId);
}
