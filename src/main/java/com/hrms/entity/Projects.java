package com.hrms.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Projects {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String clientName;
	@Column(nullable = false)
	private String companyName;
	@Column(nullable = false)
	private String phoneNumber;
	@Column(nullable = false)
	private String emailId;
	private String countryName;
	private boolean projectStatus;
	@Column(unique = true, nullable = false)
	private String projectTitle;
	@Column(unique = true, nullable = false)
	private String projectId;
	@Column(nullable = false)
	private String projectManager;
	@Column(nullable = false)
	private int teamSize;
	private String usedFor;
	@Column(nullable = false)
	private LocalDate startDate;
	@Column(nullable = false)
	private LocalDate endDate;

	private String description;
	private String technologyUsed;
	private String skillsRequired;

	@Column(nullable = false)
	private String filePath;

	@ManyToMany
	@JsonIgnore
	private List<Employee> employee;
	
	@ManyToOne
	@JsonIgnore
	private Branch branch;
}
