package com.hrm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtils {
	static Connection getConenctionTODatabase() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		ResourceBundle rb = ResourceBundle.getBundle("dbdetails");
		return DriverManager.getConnection(rb.getString("url"), rb.getString("username"), rb.getString("password"));
	}
	
	static void closeConenction(Connection con) throws SQLException {
		if(con!=null) {
			con.close();
		}
	}
	
	static boolean isResultSetEmpty(ResultSet rs) throws SQLException {
		if(!rs.isBeforeFirst() && rs.getRow()==0) {
			return true;
		}
		
		return false;
	}
}
