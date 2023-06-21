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
public class WithdrawController {
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value = "/user/account/withdraw", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> withdrawAmount(
			@RequestParam(name = "accountId") int accountId, @RequestParam(name = "amount") int amount, 
			@RequestParam(name = "pin") int pin)throws JsonProcessingException{
		
		try {
			String message = accountService.withdraw(accountId, amount, pin);
			
			return ResponseEntity.ok().body(" {\"message\": " + message + "}");
		}catch(Exception err) {}
		return ResponseEntity.status(400).body(" {\"message\": \"Transaction not Successful\"}");	
	}

}
