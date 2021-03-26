package com.prokarma.engineering.excellence.customer.publisher.exception;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.apache.kafka.common.errors.InvalidRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
// import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.prokarma.engineering.excellence.customer.publisher.converter.ObjectToJson;
import com.prokarma.engineering.excellence.customer.publisher.domain.CustomerFailureResponse;

@ControllerAdvice
public class CustomerPublisherControllerAdvice {

  @ExceptionHandler(AuthenticationException.class)
  public final ResponseEntity<Object> handleException(
      AuthenticationException ex, HttpServletRequest request) {
    CustomerFailureResponse failureResponse = new CustomerFailureResponse();
    failureResponse.setErrorType(TokenException.class.getSimpleName());
    failureResponse.setMessage("Token Error" + ex.getMessage());
    failureResponse.setStatus("Failed");
    return new ResponseEntity<>(failureResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(InvalidRequestException.class)
  public final ResponseEntity<Object> handleException(
      InvalidRequestException ex, HttpServletRequest request) {
    CustomerFailureResponse failureResponse = new CustomerFailureResponse();
    failureResponse.setErrorType(InvalidRequestException.class.getSimpleName());
    failureResponse.setMessage("Request error" + ex.getMessage());
    failureResponse.setStatus("Failed");
    return new ResponseEntity<>(failureResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(GeneralException.class)
  public final ResponseEntity<CustomerFailureResponse> handleException(
      GeneralException ex, HttpServletRequest request) {
    CustomerFailureResponse failureResponse = new CustomerFailureResponse();
    failureResponse.setErrorType(GeneralException.class.getSimpleName());
    failureResponse.setMessage("General Error" + ex.getMessage());
    failureResponse.setStatus("Failed");
    return new ResponseEntity<CustomerFailureResponse>(
        failureResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<CustomerFailureResponse> handleException(NoHandlerFoundException ex, HttpServletRequest request) {
    CustomerFailureResponse customerFailureResponse = new CustomerFailureResponse();
    customerFailureResponse.setMessage("General Error. " + ex.getMessage());
    customerFailureResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
    customerFailureResponse.setErrorType(GeneralException.class.getSimpleName());
    return new ResponseEntity<>(customerFailureResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(TokenException.class)
  public final ResponseEntity<CustomerFailureResponse> handleException(
      TokenException ex, HttpServletRequest request) {
    CustomerFailureResponse failureResponse = new CustomerFailureResponse();
    failureResponse.setErrorType(TokenException.class.getSimpleName());
    failureResponse.setMessage("Token Error" + ex.getMessage());
    failureResponse.setStatus("Failed");
    return new ResponseEntity<CustomerFailureResponse>(failureResponse, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<CustomerFailureResponse> handleException(MethodArgumentNotValidException ex, HttpServletRequest request) {
    Map<String, TreeSet<String>> fieldValidationError = new TreeMap<>();
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
    for (FieldError fieldError : fieldErrors) {
      if (fieldValidationError.containsKey(fieldError.getField())) {
        TreeSet<String> error = fieldValidationError.get(fieldError.getField());
        error.add(fieldError.getDefaultMessage());
        fieldValidationError.put(fieldError.getField(), error);
      } else {
        TreeSet<String> error = new TreeSet<>();
        error.add(fieldError.getDefaultMessage());
        fieldValidationError.put(fieldError.getField(), error);
      }
    }
    CustomerFailureResponse customerFailureResponse = new CustomerFailureResponse();
    customerFailureResponse.setMessage("Input Request Validation Failed. " + ObjectToJson.convertToJson(fieldValidationError));
    customerFailureResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
    customerFailureResponse.setErrorType(InputMismatchException.class.getSimpleName());
    return new ResponseEntity<>(customerFailureResponse, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<CustomerFailureResponse> handleException(HttpMessageNotReadableException ex, HttpServletRequest request) {
    CustomerFailureResponse customerFailureResponse = new CustomerFailureResponse();
    customerFailureResponse.setMessage("Input Request Validation Failed. " + ex.getMessage());
    customerFailureResponse.setStatus(HttpStatus.BAD_REQUEST.toString());
    customerFailureResponse.setErrorType(GeneralException.class.getSimpleName());
    return new ResponseEntity<>(customerFailureResponse, HttpStatus.BAD_REQUEST);
  }
}
