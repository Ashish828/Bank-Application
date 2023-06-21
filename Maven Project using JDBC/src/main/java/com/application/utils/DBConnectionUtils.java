package com.application.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;

public final class DBConnectionUtils {
	private static Connection connection = null;
	
	public static final Connection getConnection() {
		if(connection == null) {
			
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system", "root", "my_sql123456789");
			} catch (SQLException | ClassNotFoundException  e) {
				System.out.print(e.getMessage());
				return connection;
			} 
		}
		return connection;
	}
	
}
