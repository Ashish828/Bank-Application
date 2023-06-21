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
@RequestMapping(value = "/user/account")
public class AccountAuthorization{

	@RequestMapping(value = "/login", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> loginAccount(
			@RequestParam(name = "id") int id, @RequestParam(name = "pin") int pin, 
			HttpSession session)throws JsonProcessingException{
		
		AppData app = AppData.getInstance();
		
		ObjectMapper mapper = new ObjectMapper();
		if(app.getCurrentCustomer() != null && app.loginUser(id, pin)) {
			//correct user
			session.setAttribute("accountID", id);
			return ResponseEntity.ok().body(mapper.writeValueAsString(app.getCurrentUser()));
		}
		else {
			return ResponseEntity.status(400).body(null);
		}
	}
	
	@RequestMapping(value = "/sign-up", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> signUpAccount(
			@RequestParam(name = "amount") int amount, @RequestParam(name = "pin") int pin, 
			HttpSession session)throws JsonProcessingException{

		AppData app = AppData.getInstance();
		boolean isCreated = app.createAccount(amount, pin);

		ObjectMapper mapper = new ObjectMapper();
		if(isCreated) {
			session.setAttribute("accountID", app.getCurrentUser().getAccountNumber());
			return ResponseEntity.ok().body(mapper.writeValueAsString(app.getCurrentUser()));
		}
		else {
			return ResponseEntity.status(400).body(null);
		}

	}

}
