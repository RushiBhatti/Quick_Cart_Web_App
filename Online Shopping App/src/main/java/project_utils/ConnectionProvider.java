package project_utils;

import java.sql.Connection;
import java.sql.DriverManager;
import project_globals.GlobalValues;

public class ConnectionProvider {

	public static Connection getConnection() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ospjava", GlobalValues.dbUserName,
					GlobalValues.dbUserPassword);
			
			return con;

		} 
		catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
