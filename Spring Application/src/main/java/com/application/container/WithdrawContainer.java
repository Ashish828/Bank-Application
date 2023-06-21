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
public class WithdrawContainer {
	
	@RequestMapping(value = "/user/account/withdraw", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> withdrawAmount(
			@RequestParam(name = "amount") int amount, @RequestParam(name = "pin") int pin, 
			HttpSession session)throws JsonProcessingException{
		
		AppData app = AppData.getInstance();
		String message = app.withdraw(amount, pin);
		
		return ResponseEntity.ok().body(" {\"message\": " + message + "}");
		
	}

}
