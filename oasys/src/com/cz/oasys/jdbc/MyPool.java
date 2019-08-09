package com.cz.oasys.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;

public class MyPool {

	// ����ע������
	private String url = "jdbc:mysql://localhost:3306/vmaxtam";
	private String user = "root";
	private String password = "root";
	private static String driverClass = "com.mysql.jdbc.Driver";

	// �������ӳ�����
	private int initSize = 5;
	private int maxSize = 8;

	// ��LinkedList����������connection����
	private LinkedList<Connection> connList = new LinkedList<Connection>();
	// ����һ����ʱ�������������Ӷ��������
	private int currentsize = 0;

	// ����MyPool����ʱ�Զ�ע������
	static {
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ��ȡ���ӵķ���
	private Connection getConnection() {
		Connection conn = null;
		try {
			conn = (Connection) DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	// ���췽������ʼ�����ӳأ���������������Ӷ���
	public MyPool() {
		for (int i = 0; i < initSize; i++) {
			Connection connection = this.getConnection();
			connList.add(connection);
			currentsize++;
		}
	}

	// ��ȡ���ӳ��е�һ�����Ӷ���
	public Connection getConnFromPool() {
		// �����ӳػ�û��
		if (connList.size() > 0) {
			Connection connection = connList.getFirst();
			connList.removeFirst();
			return connection;

		} else if (connList.size() == 0 && currentsize < 8) {
			// ���ӳر��ÿգ���������û�дﵽ���ޣ������µ�����
			currentsize++;
			connList.addLast(this.getConnection());

			Connection connection = connList.getFirst();
			connList.removeFirst();
			return connection;
		}

		throw new RuntimeException("�������ﵽ���ޣ���ȴ�");
	}

	// ����������ӷŻ����ӳ�
	public void releaseConnection(Connection connection) {
		connList.addLast(connection);
	}

}
