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
public class Leaves {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long leaveId;
	@Column(nullable = false)
	private String subject;
	@Column(nullable = false)
	private String leaveDetails;
	private String reply;
	private String attachedDocuments;
	private	LocalDate appliedDate;
	@Column(nullable = false)
	private LocalDate startDate;
	private LocalDate endDate;
	private MultiStatus status;
	
	@ManyToOne
	@JsonIgnore
	private Employee employee;
}
