package dev.cosentino.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	// only ever want one session factory 
	// the SessionFactory Interface is responsible for making the initial database connection and setting up the mappings between the entitiy and the table
	private static SessionFactory sf; 
	
	private HibernateUtil() {};
	
	public static SessionFactory getSessionFactory() {
		if(sf == null) {
			Configuration cfg = new Configuration();
			sf = cfg.configure().buildSessionFactory();
		}
		return sf;
	}
	

}
