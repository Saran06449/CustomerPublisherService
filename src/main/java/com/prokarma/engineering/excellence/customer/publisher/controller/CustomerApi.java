/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.prokarma.engineering.excellence.customer.publisher.controller;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.prokarma.engineering.excellence.customer.publisher.domain.Customer;
import com.prokarma.engineering.excellence.customer.publisher.domain.CustomerFailureResponse;
import com.prokarma.engineering.excellence.customer.publisher.domain.CustomerSuccessResponse;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-22T06:13:52.948Z")

@Validated
@Api(value = "customer", description = "the customer API")
@RequestMapping(value = "/v2")
public interface CustomerApi {

    @ApiOperation(value = "Add a new Customer", nickname = "addCustomer", notes = "", response = CustomerSuccessResponse.class, tags={ "customer", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = CustomerSuccessResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = CustomerFailureResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = CustomerFailureResponse.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = CustomerFailureResponse.class) })
    @RequestMapping(value = "/customer",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" },
        method = RequestMethod.POST)
    ResponseEntity<CustomerSuccessResponse> addCustomer(@ApiParam(value = "Authorization Token" ,required=true, defaultValue="ds4adadd-fjfj44fgf-rewr3ere-ddd32dfd") @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "Unique identifier of the request" ,required=true, defaultValue="12345") @RequestHeader(value="Activity-Id", required=true) String activityId,@ApiParam(value = "Transaction Id of the request" ,required=true, defaultValue="12345") @RequestHeader(value="Transaction-Id", required=true) String transactionId,@ApiParam(value = "Customer object that needs to be stored" ,required=true )  @Valid @RequestBody Customer body);

}
