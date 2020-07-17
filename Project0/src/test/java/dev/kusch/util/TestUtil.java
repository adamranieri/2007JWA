package dev.kusch.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import dev.kusch.utils.ConnectionUtil;

public class TestUtil {
	
	public static void setUpDb() {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "CALL set_up_bankAPIdb";
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void tearDownDb() {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "CALL tear_down_bankAPIdb";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
