package dev.alsabea.connectionUtil;

import java.sql.Connection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.alsabea.connectionUtils.ConnectionUtils;

class TestConnectionUtil{

	
	@Test
	public void testGetConnection() {
		
		Connection con= ConnectionUtils.getConnection();
		
		Assertions.assertTrue(con!=null);
	}
	
	
}
