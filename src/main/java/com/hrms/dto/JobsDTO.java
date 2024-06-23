package com.hrms.dto;

import java.time.LocalDate;

import com.hrms.entity.Branch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobsDTO {
	private Long id;
	private String title;
	private String department;
	private String openings;
	private String qualification;
	private String skills;
	private String amount;
	private String experience;
	private LocalDate applyDate;
	private String type;
	private String responsibilities;
	private String requirements;
	
	private Branch branch;
}
