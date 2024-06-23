package com.hrms.exception;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {
	private String resourceName;
	private String fieldName;
	private long fieldValue;
	private String stringFieldValue;

	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public ResourceNotFoundException(String resourceName, String fieldName, String stringFieldValue) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, stringFieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.stringFieldValue = stringFieldValue;
	}

	public ResourceNotFoundException(String resourceName) {
		super(String.format("%s not found.", resourceName));
		this.resourceName = resourceName;
	}

}
