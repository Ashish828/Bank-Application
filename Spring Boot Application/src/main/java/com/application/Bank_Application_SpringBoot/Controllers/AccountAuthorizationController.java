package com.application.Bank_Application_SpringBoot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.Bank_Application_SpringBoot.Model.Account;
import com.application.Bank_Application_SpringBoot.Services.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping(value = "/user/account")
public class AccountAuthorizationController{
	
	@Autowired
	AccountService accountService;

	@RequestMapping(value = "/login", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> loginAccount(
			@RequestParam(name = "id") int id, @RequestParam(name = "pin") int pin)throws JsonProcessingException{
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			if(accountService.loginAccount(id, pin)) {
				//correct user
				return ResponseEntity.ok().body(mapper.writeValueAsString(accountService.findAccountById(id)));
			}
		}
		catch(Exception err) {}
		return ResponseEntity.status(400).body(null);
	}
	
	@RequestMapping(value = "/sign-up", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> signUpAccount(
			@RequestParam(name = "amount") int amount, @RequestParam(name = "pin") int pin,
			@RequestParam(name = "bankId") int bankId, @RequestParam(name = "customerId") int customerId
			)throws JsonProcessingException{

		try {
			int accountId = accountService.createAccount(bankId, customerId, amount, pin);

			ObjectMapper mapper = new ObjectMapper();
			if(accountId != -1) {
				Account account = accountService.findAccountById(accountId);
				return ResponseEntity.ok().body(mapper.writeValueAsString(account));
			}
		}
		catch (Exception e) {}
		return ResponseEntity.status(400).body(null);

	}
	
	@RequestMapping(value = "/is-new-account", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> isNewAccount(
			@RequestParam(name = "bankId") int bankId, @RequestParam(name = "customerId") int customerId
			)throws JsonProcessingException{

		try {
			if(accountService.isNewAccount(bankId, customerId)) {
				return ResponseEntity.ok().body("{\"isNew\": true }");
			}
			else {
				return ResponseEntity.status(200).body("{\"isNew\": false }");
			}
		}
		catch (Exception e) {
			return ResponseEntity.status(400).body(null);
		}
	}

}
