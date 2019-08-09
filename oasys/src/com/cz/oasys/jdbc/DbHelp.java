package com.cz.oasys.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DbHelp {

	public static final String url = "jdbc:mysql://127.0.0.1/oa?character=utf-8";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "root";
	public Connection conn = null;
	public PreparedStatement pst = null;

	public DbHelp() {
	
	}

	
	public DbHelp(String sql) {
		try {
			Class.forName(name);// 指定连接类型 (加载驱动)
			conn = (Connection) DriverManager.getConnection(url, user, password);// 获取连接
			pst = (PreparedStatement) conn.prepareStatement(sql);// 准备执行语句
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			Class.forName(name);// 指定连接类型 (加载驱动)
			conn = (Connection) DriverManager.getConnection(url, user, password);// 获取连接
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

		// pst = (PreparedStatement) conn.prepareStatement(sql);//准备执行语句
	}

	public void closeConn(Connection conn) {
		try {
			conn.close();
			//this.pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
