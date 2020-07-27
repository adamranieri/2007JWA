package dev.alsabea.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionEstablisher {

	private static Connection con;

	private ConnectionEstablisher() {

	}

	public static Connection getConnection() {
		/*
		 * if con is null, initialize it and then return, if con is not null, just
		 * return it, (that is why there is no else statement because either way I
		 * should return con)
		 */

		if (con == null) {

			Properties props = new Properties();
			FileInputStream in = null;
			try {
				in = new FileInputStream("src/main/resources/connection-credentials.properties");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

			try {
				props.load(in);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String url = props.getProperty("url");
			String user = props.getProperty("user");
			String pass = props.getProperty("pass");
			try {
				con = DriverManager.getConnection(url, user, pass);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return con;
	}
	
	
}