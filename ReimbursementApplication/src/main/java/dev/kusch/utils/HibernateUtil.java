package dev.kusch.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	// we only ever want one SessionFactory in an application
	// the SessionFactory Interface is responsible for making the initial database connection
	// and setting up the mappings between the entity and the table
	private static SessionFactory sf;
	
	private HibernateUtil() {}
	
	public static SessionFactory getSessionFactory() {
		if (sf == null) {
			// this constructor with no arguments looks for hibernate.cfg.xml and populates
			// the cfg object with the information
			Configuration cfg = new Configuration();
			
			sf = cfg.configure().buildSessionFactory();
			return sf;
		} else {
			return sf;
		}
	}
}
