package com.application.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.application.model.Bank;
import com.application.utils.HibernateConnectionUtils;

class BankRepository {
	
	@SuppressWarnings("unchecked")
	static final Set<Bank> getAllBanks(){
		Session session = HibernateConnectionUtils.getHibernateSession();
		Query query = session.createQuery("from Bank");
		Set<Bank> allBanks = new HashSet<>(query.list());
		HibernateConnectionUtils.closeHibernateSession(session);
		return allBanks;
	}

}
