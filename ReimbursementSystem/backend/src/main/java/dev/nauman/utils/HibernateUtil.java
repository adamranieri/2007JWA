package dev.nauman.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
private static SessionFactory sf;
	

	private HibernateUtil() {};
	public static SessionFactory getSessionFactory() {
		if (sf==null) {
			Configuration cfg = new Configuration(); //this constructor with no arguments is programmed to look for that configuration file and populate it 
			
			sf = cfg.configure().buildSessionFactory();
		}
		return sf;
	}

}
