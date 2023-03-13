package com.mycompany.propertymanagement.exception;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BusinessException extends RuntimeException{
    
    private List<ErrorModel> errors;

    public BusinessException(List<ErrorModel> errors){
        this.errors = errors;
    }
    
}
