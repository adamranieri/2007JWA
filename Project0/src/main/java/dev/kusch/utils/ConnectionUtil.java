package dev.kusch.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	
	public static Connection getConnection() {
		try {
			// jdbc:mariadb://<databaseEndpoint>:<port#>/<database_name>?user=<user>&password=<pass>
			Properties props = new Properties();
			FileInputStream fin = new FileInputStream("src/main/resources/connection.properties");
			props.load(fin);
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
	
	public static void main(String[] args) {
		getConnection();
	}
}
