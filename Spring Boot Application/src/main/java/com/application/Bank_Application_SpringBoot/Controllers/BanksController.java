package com.application.Bank_Application_SpringBoot.Controllers;

import com.application.Bank_Application_SpringBoot.Model.Bank;
import com.application.Bank_Application_SpringBoot.Services.BankService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/user")
public class BanksController{
	
	@Autowired
	BankService bankService;
       
	@RequestMapping(value = "/get-all-banks", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getAllBanks()throws JsonProcessingException{
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			Set<Bank> banks = bankService.getBanks();
			if(banks.size() != 0) {
				return ResponseEntity.ok().body(mapper.writeValueAsString(banks));
			}
		}
		catch (Exception e) {System.out.println(e);}
		return ResponseEntity.status(400).body(null);

	}
}
