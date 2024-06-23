package com.hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OmsEnquiryDTO {
	private Long enqId;
	private String name;
	private String email;
	private String subject;
	private String description;
}
