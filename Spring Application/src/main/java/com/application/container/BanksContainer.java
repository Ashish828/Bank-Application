package com.application.container;

import com.application.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.dao.AppData;

@RestController
@RequestMapping(value = "/user")
public class BanksContainer{
       
	@RequestMapping(value = "/select-bank", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> selectBank(
			@RequestParam(name = "bankName") String bankName,
			HttpSession session)throws JsonProcessingException{
		
		AppData app = AppData.getInstance();
		Bank bank = app.selectBank(bankName);
		
		ObjectMapper mapper = new ObjectMapper();
		if(bank == null) {
			return ResponseEntity.status(400).body(null);
		}else {
			return ResponseEntity.ok().body(mapper.writeValueAsString(bank));
		}
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/get-all-banks", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getAllBanks(HttpSession session)throws JsonProcessingException{
		
		ObjectMapper mapper = new ObjectMapper();
		AppData app = AppData.getInstance();
		
		if(app.getBanks().size() == 0) {
			return ResponseEntity.status(400).body(null);
		}else {
			return ResponseEntity.ok().body(mapper.writeValueAsString(AppData.getBanks()));
		}
	}
}
