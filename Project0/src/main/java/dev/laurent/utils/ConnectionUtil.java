package dev.laurent.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	
	
	public static Connection getConnection() {
		
		try {			
			Properties props = new Properties();
			FileInputStream in = new FileInputStream("src/main/resources/connection.properties");
			props.load(in);
			String details =  props.getProperty("condetails");			
			Connection conn = DriverManager.getConnection(details);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
}
