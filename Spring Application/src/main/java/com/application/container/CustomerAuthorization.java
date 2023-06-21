package com.application.container;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.dao.AppData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/user")
public class CustomerAuthorization {

	@RequestMapping(value = "/login", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> loginCustomer(
			@RequestParam(name = "id") int id, @RequestParam(name = "password") int password, 
			HttpSession session)throws JsonProcessingException{
		
		AppData app = AppData.getInstance();
		Boolean isLogged = app.loginCustomer(id, password);
		
		ObjectMapper mapper = new ObjectMapper();
		if(isLogged) {
			session.setAttribute("customerID", id);
			return ResponseEntity.ok().body(mapper.writeValueAsString(app.getCurrentCustomer()));
		}else {
			return ResponseEntity.status(400).body(null);
		}
	}
	
	@RequestMapping(value = "/sign-up", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> customerSignUp(
			@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName,
			@RequestParam(name = "address") String address, @RequestParam(name = "email") String email,
			@RequestParam(name = "password") int password, HttpSession session)throws JsonProcessingException{

		AppData app = AppData.getInstance();
		boolean isCreated = app.createCustomer(firstName, lastName, address, email, password);

		ObjectMapper mapper = new ObjectMapper();
		if(isCreated) {
			session.setAttribute("customerID", app.getCurrentCustomer().getId());
			return ResponseEntity.ok().body(mapper.writeValueAsString(app.getCurrentCustomer()));
		}else {
			return ResponseEntity.status(400).body(null);
		}

	}

}
