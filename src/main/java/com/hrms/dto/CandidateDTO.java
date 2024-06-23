package com.hrms.dto;

import java.time.LocalDate;

import com.hrms.entity.Jobs;
import com.hrms.enums.MultiStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDTO {
	private Long candidateId;
	private String candidateName;
	private String candidateEmail;
	private String candidateContactNumber;
	private String candidateCvPath;
	private LocalDate appliedDate;
	private MultiStatus status;
	
	private String location;

	private Jobs appliedFor;
}
