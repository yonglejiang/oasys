package com.cz.oasys.jdbc;

import java.util.Vector;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import com.mysql.jdbc.Connection;


public class ConnectionPool {
	private static String jdbcDriver="com.mysql.jdbc.Driver";//mysql���ݿ�����
	private static String dbUrl = "jdbc:mysql://localhost/oa?character=utf-8";
	private static String dbUser = "root";
	private static String dbPassword = "root";
	
	private String testTable="";//�������ӳز��Ե����ݿ��,Ĭ���ÿ�
	private int initialConnections = 10;//���ӳس�ʼ��С
	private int maxConnection = 50;//���ӳ���������
	private int increamentConnections = 5;//���ӳز�����ʱ,ÿ������5��
	
	private Vector connections = null;//������ӳ��е�����,vector ��һ����list�Ƶ�����
	
	
	
	//��һ����̬˽�г�Ա����
	//volatile ��֤���̷߳���ʱ,instance �����Ŀɼ���
	//ͬʱ���Ա�����instance��ʼ��ʱ�����������Ի�û�и�ֵ��
	private static volatile ConnectionPool instance;
	
	private ConnectionPool(String jdbcDriver,String dbUrl,String dbUser,
			String dbPassword){
		this.jdbcDriver = jdbcDriver;
		this.dbUrl = dbUrl;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
	}
	
	//ʹ��ͬ���ķ�ʽ�������ӳ�
	//synchronized ����ؼ�����ͬ��������˼,ȷ��ͬһʱ�̸÷������ᱻ�ظ�����
	private synchronized void createPool() throws Exception{
		
		//������ӳ��Ѿ�������,�˳��÷���
		if (connections != null) {
			System.out.println("���ӳ��Ѿ�����,�����ظ�����");
			return;
		}
		
		//������ӳ�δ������
		Class.forName(jdbcDriver);//��������
		
		//��ʼ���洢�������ӵ�����
		connections = new Vector();
		//���ݳ�ʼֵinitailConnections,�����ӳ��д�������
	//createConnections(initialConnections);//
		System.out.println("���ݿ����ӳش����ɹ�");
		
	}

}
