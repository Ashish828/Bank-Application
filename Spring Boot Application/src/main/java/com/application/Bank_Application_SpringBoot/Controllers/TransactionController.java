package com.application.Bank_Application_SpringBoot.Controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.Bank_Application_SpringBoot.Model.Transaction;
import com.application.Bank_Application_SpringBoot.Services.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TransactionController{
       
	@Autowired
	TransactionService transactionService;
	
	@RequestMapping(value = "/user/account/transactions", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getAccountTransactions(
			@RequestParam(name = "accountId") int accountId)throws JsonProcessingException{
		
		try {
			Set<Transaction> transactions = transactionService.getAllTransactions(accountId);

			ObjectMapper mapper = new ObjectMapper();
			return ResponseEntity.ok().body(mapper.writeValueAsString(transactions));
		}catch(Exception err) {};
		return ResponseEntity.status(400).body(null);
	}

}
