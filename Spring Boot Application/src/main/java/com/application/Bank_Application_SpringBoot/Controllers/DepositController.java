package com.application.Bank_Application_SpringBoot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.Bank_Application_SpringBoot.Services.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class DepositController{
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value = "/user/account/deposit", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> depositAmount(
		@RequestParam(name = "amount") int amount, @RequestParam(name = "senderAccountId") int senderAccountId, 
		@RequestParam(name = "receiverAccountId") int receiverAccountId, @RequestParam(name = "pin") int pin
		)throws JsonProcessingException{

		try {
			String message = accountService.deposit(senderAccountId, receiverAccountId, amount, pin);
			return ResponseEntity.ok().body(" {\"message\": " + message + "}");
		}catch (Exception e) {}	
		return ResponseEntity.status(400).body("{\"message\": \"Transaction not Successful\" }");
	}


}
