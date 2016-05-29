package com.order.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 加载数据库连接
 */
public class DBConnection {
	private final String url = "jdbc:mysql://localhost:3306/robot";
	private final String username = "root";
	private final String password = "123";
	private Connection conn;
	
	private void init(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		if(conn==null){
			init();
		}
		return this.conn;
	}

	public static void main(String[] args) {
		new DBConnection().getConnection();
		System.out.println("成功建立连接");
	}
}
