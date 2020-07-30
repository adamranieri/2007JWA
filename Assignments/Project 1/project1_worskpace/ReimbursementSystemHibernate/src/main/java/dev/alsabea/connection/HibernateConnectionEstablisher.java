package dev.alsabea.connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnectionEstablisher {
	
	private static SessionFactory sf;
	
	private HibernateConnectionEstablisher() {};
	
	public static SessionFactory getSessionFactory() {
		
		if(sf == null)
			sf = (new Configuration()).configure().buildSessionFactory();
		
		return sf;
	}
	

}
