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

@RestController
public class DepositContainer{
	
	@RequestMapping(value = "/user/account/deposit", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> depositAmount(
			@RequestParam(name = "amount") int amount, @RequestParam(name = "pin") int pin, 
			@RequestParam(name = "recieveAccountNo") int recieveAccountNo, HttpSession session)throws JsonProcessingException{
		
		AppData app = AppData.getInstance();
		String message = app.deposit(amount, pin, recieveAccountNo);
		
		return ResponseEntity.ok().body(" {\"message\": " + message + "}");
		
	}


}
