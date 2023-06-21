package com.application.Bank_Application_SpringBoot.Model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "customer")
public class Customer{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY ) 
    private int id;
	@Column(name="first_name")
    private String firstName;
	@Column(name="last_name")
    private String lastName;
    private String email;
    private String address;
    @Column(name="password")
    private int password;
    @OneToMany(mappedBy = "customerDetails", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Account> accounts= new HashSet<>();

    public Customer() {
    }
    
    public Customer(String firstName, String lastName, String email, String address, int password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.password = password;
    }    
    
    public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public int getPassword() {
		return password;
	}



	public void setPassword(int password) {
		this.password = password;
	}



	public Set<Account> getAccounts() {
		return accounts;
	}



	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

    @Override
    public String toString() {
        return "ID: " + id +
                ", First Name: " + firstName+
                ", Last Name: " + lastName+
                ", Email: '" + email+
                ", Address: " + address;
    }
}

