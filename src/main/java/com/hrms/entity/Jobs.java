package com.hrms.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Jobs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String department;
	private String openings;
	@Column(nullable = false)
	private String qualification;
	@Column(nullable = false)
	private String skills;
	private String amount;
	private String experience;
	private LocalDate applyDate;
	private String type;
	private String responsibilities;
	private String requirements;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Branch branch;

	@OneToMany(mappedBy = "appliedFor")
	@JsonIgnore
	private List<Candidate> candidate;

}
