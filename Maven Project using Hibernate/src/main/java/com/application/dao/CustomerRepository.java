package com.application.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.application.model.Customer;
import com.application.utils.HibernateConnectionUtils;

class CustomerRepository {
	static final Customer createCustomer(String firstName, String lastName, String address, String email, int phoneNo) {
		 try {
         	Session session = HibernateConnectionUtils.getHibernateSession();
         	Customer newCustomer = new Customer( firstName, lastName, email, address, phoneNo);
         	Transaction transaction = session.beginTransaction();
         	session.save(newCustomer);
         	transaction.commit();
         	HibernateConnectionUtils.closeHibernateSession(session);
         	
         	return newCustomer;
         	
         }catch(Exception err) {
         	return null;
         }
	}
	
	static final Customer loginCustomer(int customerID, long phoneNumber) {
		 try {
			Session session = HibernateConnectionUtils.getHibernateSession();
			Transaction tx = session.beginTransaction();
			Customer customer = (Customer)session.get(Customer.class, customerID);
			tx.commit();
			HibernateConnectionUtils.closeHibernateSession(session);
        	if(customer != null && customer.getPhoneNumber() == phoneNumber)return customer;
        	return null;
        }catch(Exception err) {
        	return null;
        }
	}
}
