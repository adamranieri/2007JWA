package dev.alsabea.connection;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestHibernateConnection {

	@Test
	final void testConnection() {
		SessionFactory sf= HibernateConnectionEstablisher.getSessionFactory();
		
		Assertions.assertNotNull(sf);
	}

}
