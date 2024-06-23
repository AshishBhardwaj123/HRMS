package com.hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OmsImagesDTO {
	private Long id;
	private Long imgId;
	private String imgPath;
	private String s3Key;
}
