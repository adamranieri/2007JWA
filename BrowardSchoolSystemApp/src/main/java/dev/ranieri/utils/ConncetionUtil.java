package dev.ranieri.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConncetionUtil {

	// JDBC (Java DataBase Connectivity)
	// a library for interacting with databases
	// Core interfaces
	// Connection - establishing a connection to a db
	// PreparedStatement - executing sql statements
	// CallableStatement - executing sql stored procedures
	// ResultSet - stores the results of a SQL query	
	
	public static Connection createConnection() {
		
		try {
			//create a properties object to store information
			Properties props = new Properties();
			
			// use file io to read in a file
			FileInputStream in = new FileInputStream("src/main/resources/connection.properties");
			// populate the properties object by loading in the fileinputstream 
			props.load(in);
			
			String details = props.getProperty("condetails");
			
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
