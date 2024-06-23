package com.hrms.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hrms.enums.MultiStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Candidate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long candidateId;
	@Column(nullable = false)
	private String candidateName;
	@Column(nullable = false)
	private String candidateEmail;
	@Column(unique = true, nullable = false)
	private String candidateContactNumber;
	private String candidateCvPath;
	private LocalDate appliedDate;
	private MultiStatus status;

	@ManyToOne
	@JsonIgnore
	private Jobs appliedFor;

}
