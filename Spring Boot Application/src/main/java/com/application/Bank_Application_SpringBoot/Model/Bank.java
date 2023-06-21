package com.application.Bank_Application_SpringBoot.Model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "bank")
public class Bank {
	@Id
    private int id;
    private String name;
    @OneToMany(mappedBy = "bank", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Account> accounts = new HashSet<>();
    

	 public Bank(int id, String name) {
	    this.id = id;
	    this.name = name;
	}
	 
	 public Bank() {
	 }
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Set<Account> getAccounts() {
		return accounts;
	}
	
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

}

