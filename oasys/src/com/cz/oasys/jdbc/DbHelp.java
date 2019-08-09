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
			Class.forName(name);// ָ���������� (��������)
			conn = (Connection) DriverManager.getConnection(url, user, password);// ��ȡ����
			pst = (PreparedStatement) conn.prepareStatement(sql);// ׼��ִ�����
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			Class.forName(name);// ָ���������� (��������)
			conn = (Connection) DriverManager.getConnection(url, user, password);// ��ȡ����
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

		// pst = (PreparedStatement) conn.prepareStatement(sql);//׼��ִ�����
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
