package com.application.Bank_Application_SpringBoot.Services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.Bank_Application_SpringBoot.Model.Bank;
import com.application.Bank_Application_SpringBoot.Repositories.BankRepository;

@Service
public class BankService {
	
	@Autowired
	private BankRepository bankRepository;

	public Set<Bank> getBanks() {
		Set<Bank> banks = new HashSet<>();
		
	    for (Bank bank: bankRepository.findAll()) {
	    	banks.add(bank);
	    }

		return banks;
	}
	
	public Bank getBankById(int id) {
		return bankRepository.findById(id).orElse(null);
	}
    
}
