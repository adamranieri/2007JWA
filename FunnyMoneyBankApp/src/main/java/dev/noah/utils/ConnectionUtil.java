package dev.noah.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

	public static void main(String[] args) {
		getConnection();
	}
	
	public static Connection getConnection() {
		
		try {
			Properties props = new Properties();
			FileInputStream stream = new FileInputStream("src/main/resources/connections.properties");
			props.load(stream);
			String info = props.getProperty("connInfo");
			Connection conn = DriverManager.getConnection(info); 
			//System.out.println(conn);
			return conn;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
