package com.prokarma.engineering.excellence.customer.publisher.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJson {

  public static String convertToJson(Object object) {

    ObjectMapper mapper = new ObjectMapper();
    String jsonString = new String();
    try {
      jsonString = mapper.writeValueAsString(object);
      
    } catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return jsonString;
  }
}
