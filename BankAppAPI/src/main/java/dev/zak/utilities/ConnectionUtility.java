package dev.zak.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtility {

	public ConnectionUtility() {
		// TODO Auto-generated constructor stub
	}
	
	public static Connection getConnection() {
		return connectbyDbProperty("dbCon");
	}
	
	public static Connection getConnectionTest() {
		return connectbyDbProperty("dbConTest");
	}
	
	private static Connection connectbyDbProperty(String db) {
		try {
			Properties p =new Properties();
			FileInputStream in = new FileInputStream("src/main/resources/conDetails.properties");
			p.load(in);
			String dbCon = p.getProperty(db);
			
			// String dbCon = "jdbc:mariadb://awsdb.ccvptbwtj1a8.us-east-2.rds.amazonaws.com:3306/monschool_db?user=admin&password=admin1234";
			
			Connection con = DriverManager.getConnection(dbCon);
			// System.out.println(con);
		
			return con;
		}catch(SQLException e) {
			e.printStackTrace();
		}catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
