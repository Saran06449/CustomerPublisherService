package com.prokarma.engineering.excellence.customer.publisher.controller;

import com.prokarma.engineering.excellence.customer.publisher.converter.CustomerMaskConverter;
import com.prokarma.engineering.excellence.customer.publisher.converter.ObjectToJson;
import com.prokarma.engineering.excellence.customer.publisher.domain.Customer;
import com.prokarma.engineering.excellence.customer.publisher.domain.CustomerFailureResponse;
import com.prokarma.engineering.excellence.customer.publisher.domain.CustomerSuccessResponse;
import com.prokarma.engineering.excellence.customer.publisher.service.CustomerPublisherService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@javax.annotation.Generated(
    value = "io.swagger.codegen.languages.SpringCodegen",
    date = "2021-03-22T06:13:52.948Z")
@RestController
@Validated
@RequestMapping(value = "/createCustomer")
public class CustomerPublisherController implements CustomerApi {

  private static final Logger log = LoggerFactory.getLogger(CustomerPublisherController.class);
  
  @Autowired
  private CustomerMaskConverter customerMaskConverter;
  
  @Autowired
  private CustomerPublisherService publisherService;
  
  @ApiOperation(value = "Publish Message to Kafka", nickname = "publishMessage", notes = "", response = CustomerSuccessResponse.class,
	      tags = {
	        "publish",
	      })
	  @ApiResponses(value = {
	          @ApiResponse(code = 201, message = "Success Response", response = CustomerSuccessResponse.class),
	          @ApiResponse(code = 400, message = "Failure Response", response = CustomerFailureResponse.class),
	          @ApiResponse(code = 401, message = "Failure Response", response = CustomerFailureResponse.class),
	          @ApiResponse(code = 403, message = "Failure Response", response = CustomerFailureResponse.class),
	          @ApiResponse(code = 404, message = "Failure Response", response = CustomerFailureResponse.class),
	          @ApiResponse(code = 500, message = "Failure Response", response = CustomerFailureResponse.class)
	      })
  @PostMapping(value = "/add", produces = "application/json", consumes = "application/json")
  public ResponseEntity<CustomerSuccessResponse> addCustomer(
      @ApiParam(
              value = "Authorization Token",
              required = true,
              defaultValue = "ds4adadd-fjfj44fgf-rewr3ere-ddd32dfd")
          @RequestHeader(value = "Authorization", required = true)
          String authorization,
      @ApiParam(value = "Unique identifier of the request", required = true, defaultValue = "12345")
          @RequestHeader(value = "Activity-Id", required = true)
          String activityId,
      @ApiParam(value = "Transaction Id of the request", required = true, defaultValue = "12345")
          @RequestHeader(value = "Transaction-Id", required = true)
          String transactionId,
      @ApiParam(value = "Customer object that needs to be stored", required = true)
          @Valid
          @RequestBody
          Customer body) {
	  
	  log.info("customer request received:", ObjectToJson.convertToJson(customerMaskConverter.maskCustomer(body)));
	  CustomerSuccessResponse successResponse = publisherService.sendToKafka(ObjectToJson.convertToJson(body));
	  log.info("customer response sent:", ObjectToJson.convertToJson(successResponse));
	  return new ResponseEntity<CustomerSuccessResponse>(successResponse, HttpStatus.OK);
  }
}
