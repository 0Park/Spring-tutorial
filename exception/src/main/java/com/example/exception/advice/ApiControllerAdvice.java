package com.example.exception.advice;

import com.example.exception.controller.ApiController;
import com.example.exception.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.exception.dto.Error;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestControllerAdvice(basePackageClasses = ApiController.class)
public class ApiControllerAdvice {
    @ExceptionHandler(value=Exception.class)
    public ResponseEntity exception(Exception e){
        System.out.println(e.getClass().getName());
        /*System.out.println("--------------------------------------");
        System.out.println(e.getLocalizedMessage());
        System.out.println("--------------------------------------");*/
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest){
        List<Error> errorList = new ArrayList<>();

        BindingResult bindingResult  = e.getBindingResult();

        bindingResult.getAllErrors().forEach(error ->{
            FieldError field = (FieldError) error;
            String fieldName = field.getField();
            String message = field.getDefaultMessage();
            String value = field.getRejectedValue().toString();

//            System.out.println("-------------------");
//            System.out.println(fieldName);
//            System.out.println(message);
//            System.out.println(value);

            Error errors= new Error();
            errors.setField(fieldName);
            errors.setMessage(message);
            errors.setInvalidValue(value);

            errorList.add(errors);
        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value= ConstraintViolationException.class)
    public ResponseEntity constraintViolationException (ConstraintViolationException e,HttpServletRequest httpServletRequest) {

            List<Error> errorList = new ArrayList<>();

            e.getConstraintViolations().forEach(error-> {

            String message = error.getMessage();
            String invalidValue = error.getInvalidValue().toString();
            Stream<Path.Node> stream = StreamSupport.stream(error.getPropertyPath().spliterator(), false);
            List<Path.Node> list = stream.collect(Collectors.toList());
            String field = list.get(list.size()-1).getName();

//            System.out.println("-------------------");
//            System.out.println(field);
//            System.out.println(message);
//            System.out.println(invalidValue);
                Error errors= new Error();
                errors.setField(field);
                errors.setMessage(message);
                errors.setInvalidValue(invalidValue);

                errorList.add(errors);

        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    @ExceptionHandler(value=MissingServletRequestParameterException.class)
    public ResponseEntity  missingServletRequestParameterException(MissingServletRequestParameterException e,HttpServletRequest httpServletRequest){
        List<Error> errorList = new ArrayList<>();
        String fieldName = e.getParameterName();
        String fieldType = e.getParameterType();
        String invalidValue=e.getMessage();

//        System.out.println("--------------------------");
//        System.out.println(fieldName);
//        System.out.println(fieldType);
//        System.out.println(invalidValue);

        Error errors= new Error();
        errors.setField(fieldName);
        errors.setMessage(e.getMessage());

        errorList.add(errors);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


}
