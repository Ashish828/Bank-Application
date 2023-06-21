package com.application.container;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.dao.AppData;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class LogoutContainer {
	
	@RequestMapping(value = "/user/account/logout", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getAccountTransactions(HttpSession session)throws JsonProcessingException{
		
		AppData app = AppData.getInstance();
		app.logout();
		session.setAttribute("customerID", null);
		session.setAttribute("accountID", null);
		
		return ResponseEntity.ok().body(" {\"message\": \"success\"}");
		
	}


}
