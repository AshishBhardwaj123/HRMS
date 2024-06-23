package com.hrms.dto;

import java.time.LocalDate;
import java.util.List;

import com.hrms.entity.Department;
import com.hrms.entity.Leaves;
import com.hrms.enums.RoleStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeForLeaveDTO {
	private Long id;
	private String employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private LocalDate dob;
	private String maritalStatus;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String gender;
	private String nationality;
	private String ctc;
	private String employeeType;
	private String designation;
	private int days;
	private LocalDate joiningDate;
	private String status;
	private String type;
	private String slackId;
	private String skypeId;
	private String githubId;
	
	private RoleStatus employeeRole;

	private String profilePath;
	private String appoinmentLetterPath;
	private String relivingLetterPath;
	private String experienceLetterPath;

	private Department department;

	private List<Leaves> leaveList;
}
