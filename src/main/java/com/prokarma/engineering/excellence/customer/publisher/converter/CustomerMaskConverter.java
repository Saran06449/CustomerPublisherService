package com.prokarma.engineering.excellence.customer.publisher.converter;

import org.springframework.stereotype.Component;

import com.prokarma.engineering.excellence.customer.publisher.domain.Customer;

@Component
public class CustomerMaskConverter {
	
	public Customer maskCustomer(Customer request) {
		
		Customer customer = new Customer();
		customer.setAddress(request.getAddress());
		customer.setCountry(request.getCountry());
		customer.setCountryCode(request.getCountryCode());
		customer.setCustomerStatus(request.getCustomerStatus());
		customer.setFirstName(request.getFirstName());
		customer.setLastName(request.getLastName());
		customer.setMobileNumber(request.getMobileNumber());
		String customerNumber = request.getCustomerNumber();
		if(customerNumber != null && !customerNumber.isEmpty()){
			customer.setCustomerNumber(customerNumber.substring(0, customerNumber.length()-4)+"****");
		}
		String emailAddress = request.getEmail();
		if(emailAddress != null && !emailAddress.isEmpty()) {
			customer.setEmail("****" + emailAddress.substring(5, emailAddress.length()-1));
		}
		customer.setBirthdate(request.getBirthdate());
		
		return customer;
	}
}
