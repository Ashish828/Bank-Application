package com.application.utils;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.application.model.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateConnectionUtils {
	private final static Configuration config = new Configuration().configure()
							.addAnnotatedClass(Bank.class)
							.addAnnotatedClass(Account.class)
							.addAnnotatedClass(Customer.class)
							.addAnnotatedClass(Transaction.class);
	
	private final static ServiceRegistry serviceRegistery = new ServiceRegistryBuilder()
										.applySettings(config.getProperties())
										.buildServiceRegistry();
	
	private final static SessionFactory factory = config.buildSessionFactory(serviceRegistery);
	
	private static Session session = null;
	
	public static final Session getHibernateSession() {
		if(session == null) {
			session = factory.openSession();
		}
		return session;
	}
	public static final void closeHibernateSession(Session session) {
		session.clear();
		session.close();
		HibernateConnectionUtils.session = null;
	}
	
}
