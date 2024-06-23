package com.hrms.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hrms.enums.RoleStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	private String employeeId;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(unique = true, nullable = false)
	private String phoneNumber;
	@Column(nullable = false)
	private LocalDate dob;
	private String maritalStatus;
	private String aadharCardNumber;
	private String panCardNumber;
	
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String gender;
	private String nationality;
	@Column(nullable = false)
	private String ctc;
	@Column(nullable = false)
	private String employeeType;
	@Column(nullable = false)
	private String designation;
	private int days;
	private LocalDate joiningDate;
	private String status;
	private String slackId;
	private String skypeId;
	private String githubId;
	@Column(unique = true, nullable = false)
	private String password;

	private RoleStatus employeeRole;

	private String profilePath;
	@Column(nullable = false)
	private String appoinmentLetterPath;
	private String relivingLetterPath;
	private String experienceLetterPath;

	@ManyToOne
	@JsonIgnore
	private Department department;

	@ManyToOne
	@JsonIgnore
	private Branch branch;

	@ManyToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Projects> projects;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Leaves> leaveList;
}
