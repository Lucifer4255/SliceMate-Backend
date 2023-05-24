package com.example.slicemate.Exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceAlreadyExistsException extends RuntimeException {
	String resourceName;
	String fieldName;
	Integer fieldValue;

	public ResourceAlreadyExistsException(String resourceName, String fieldName, Integer fieldValue) {
		super(String.format("%s already exists with %s : %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
}
