package com.mycompany.propertymanagement.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException businessException){
        return new ResponseEntity<>(businessException.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException methodArgumentNotValidException){
        List<ErrorModel> errors = new ArrayList<>();
        ErrorModel errorModel = null;
        List<FieldError> fieldErrorList = methodArgumentNotValidException.getFieldErrors();

        for (FieldError fieldError : fieldErrorList) {
            errorModel = new ErrorModel();
            errorModel.setCode(fieldError.getCode());
            errorModel.setMessage(fieldError.getDefaultMessage());
            errors.add(errorModel);
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    
}
