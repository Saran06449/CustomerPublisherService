package com.prokarma.engineering.excellence.customer.publisher.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prokarma.engineering.excellence.customer.publisher.domain.Customer;
import com.prokarma.engineering.excellence.customer.publisher.domain.CustomerSuccessResponse;

import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-19T12:04:46.596Z")

@Controller
public class CustomerApiController implements CustomerApi {

    private static final Logger log = LoggerFactory.getLogger(CustomerApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CustomerApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CustomerSuccessResponse> addCustomer(@ApiParam(value = "Authorization Token" ,required=true, defaultValue="ds4adadd-fjfj44fgf-rewr3ere-ddd32dfd") @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "Unique identifier of the request" ,required=true, defaultValue="12345") @RequestHeader(value="Activity-Id", required=true) String activityId,@ApiParam(value = "Application Id of the request" ,required=true, defaultValue="12345") @RequestHeader(value="Application-Id", required=true) String applicationId,@ApiParam(value = "Customer object that needs to be stored" ,required=true )  @Valid @RequestBody Customer body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CustomerSuccessResponse>(objectMapper.readValue("{  \"message\" : \"message\",  \"status\" : \"success\"}", CustomerSuccessResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CustomerSuccessResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        if (accept != null && accept.contains("application/xml")) {
            try {
                return new ResponseEntity<CustomerSuccessResponse>(objectMapper.readValue("<CustomerSuccessResponse>  <status>aeiou</status>  <message>aeiou</message></CustomerSuccessResponse>", CustomerSuccessResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/xml", e);
                return new ResponseEntity<CustomerSuccessResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CustomerSuccessResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
