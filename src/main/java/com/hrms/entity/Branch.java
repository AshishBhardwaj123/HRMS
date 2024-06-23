package com.hrms.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String branchName;
	
	@OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Employee> employee;
	
	@OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Projects> projects;
	
	@OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Jobs> jobs;

}
