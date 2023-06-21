package com.application.Bank_Application_SpringBoot.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.Bank_Application_SpringBoot.Model.Customer;
import com.application.Bank_Application_SpringBoot.Repositories.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer getCustomerById(int id) {
		try {
			return customerRepository.findById(id).orElse(null);
		}catch(Exception err) {
			return null;
		}
	}
	
    public final int createCustomer(String firstName, String lastName, String address, String email, int password){

    	try {
    		Customer newCustomer = new Customer( firstName, lastName, email, address, password);
        	customerRepository.save(newCustomer);
            return newCustomer.getId();
    	}catch(Exception err) {
    		return -1;
    	}
    }
 
	 public final boolean loginCustomer(int customerID, int password){
	
	 	try {
	 		Customer currentCustomer = getCustomerById(customerID);
	 		if(currentCustomer == null || currentCustomer.getPassword() != password)return false;
	 		return true;
	 	}catch (Exception err) {
	 		return false;
		}
	 }
}
