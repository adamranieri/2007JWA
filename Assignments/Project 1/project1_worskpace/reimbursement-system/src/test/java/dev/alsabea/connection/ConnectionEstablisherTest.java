package dev.alsabea.connection;

import java.sql.Connection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConnectionEstablisherTest {

	@Test
	void testConnection() {
		Connection con= ConnectionEstablisher.getConnection();
		
		Assertions.assertTrue(con!=null);
	}

}
