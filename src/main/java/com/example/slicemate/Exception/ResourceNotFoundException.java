package com.example.slicemate.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    String resourceName;
    String fieldName;
    Integer fieldValue;
    String fValue;
    public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldValue) {
        super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldValue));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }
    public ResourceNotFoundException(String resourceName, String fieldName, String fValue) {
        super(String.format("%s not found with %s : %s",resourceName,fieldName,fValue));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fValue=fValue;
    }
}
