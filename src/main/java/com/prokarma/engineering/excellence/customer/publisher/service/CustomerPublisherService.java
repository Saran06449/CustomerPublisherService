package com.prokarma.engineering.excellence.customer.publisher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.prokarma.engineering.excellence.customer.publisher.domain.CustomerSuccessResponse;

@Service
public class CustomerPublisherService {

  @Autowired private KafkaSender sender;

  public CustomerSuccessResponse sendToKafka(String request) {

    CustomerSuccessResponse successResponse = new CustomerSuccessResponse();

    sender.send(request);
    successResponse.setMessage("message successfully published to Kafka");
    successResponse.setStatus(HttpStatus.OK.toString());
    return successResponse;
  }
}
