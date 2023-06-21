package com.application.Bank_Application_SpringBoot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.Bank_Application_SpringBoot.Services.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/user")
public class CustomerAuthorizationController {

	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value = "/login", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> loginCustomer(
			@RequestParam(name = "id") int id, @RequestParam(name = "password") int password 
			)throws JsonProcessingException{

		try {
			Boolean isLogged = customerService.loginCustomer(id, password);
			
			ObjectMapper mapper = new ObjectMapper();
			if(isLogged) {
				return ResponseEntity.ok()
						.body(mapper.writeValueAsString(customerService.getCustomerById(id)));
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return ResponseEntity.status(400).body(null);
	}
	
	@RequestMapping(value = "/sign-up", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> customerSignUp(
			@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName,
			@RequestParam(name = "address") String address, @RequestParam(name = "email") String email,
			@RequestParam(name = "password") int password)throws JsonProcessingException{

		try {
			int customerId = customerService.createCustomer(firstName, lastName, address, email, password);

			ObjectMapper mapper = new ObjectMapper();
			if(customerId != -1) {
				return ResponseEntity.ok()
						.body(mapper.writeValueAsString(customerService.getCustomerById(customerId)));
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return ResponseEntity.status(400).body(null);
	}

}
