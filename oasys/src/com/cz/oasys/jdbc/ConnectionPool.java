package com.cz.oasys.jdbc;

import java.util.Vector;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import com.mysql.jdbc.Connection;


public class ConnectionPool {
	private static String jdbcDriver="com.mysql.jdbc.Driver";//mysql数据库驱动
	private static String dbUrl = "jdbc:mysql://localhost/oa?character=utf-8";
	private static String dbUser = "root";
	private static String dbPassword = "root";
	
	private String testTable="";//进行连接池测试的数据库表,默认置空
	private int initialConnections = 10;//连接池初始大小
	private int maxConnection = 50;//连接池最大的容量
	private int increamentConnections = 5;//连接池不够用时,每次扩容5个
	
	private Vector connections = null;//存放连接池中的连接,vector 是一个像list似的容器
	
	
	
	//定一个静态私有成员变量
	//volatile 保证多线程访问时,instance 变量的可见性
	//同时可以避免在instance初始化时其它变量属性还没有赋值完
	private static volatile ConnectionPool instance;
	
	private ConnectionPool(String jdbcDriver,String dbUrl,String dbUser,
			String dbPassword){
		this.jdbcDriver = jdbcDriver;
		this.dbUrl = dbUrl;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
	}
	
	//使用同步的方式创建连接池
	//synchronized 这个关键字是同步锁的意思,确保同一时刻该方法不会被重复调用
	private synchronized void createPool() throws Exception{
		
		//如果连接池已经被创建,退出该方法
		if (connections != null) {
			System.out.println("连接池已经创建,无需重复创建");
			return;
		}
		
		//如果连接池未被创建
		Class.forName(jdbcDriver);//加载驱动
		
		//初始化存储保存连接的容器
		connections = new Vector();
		//根据初始值initailConnections,在连接池中创建连接
	//createConnections(initialConnections);//
		System.out.println("数据库连接池创建成功");
		
	}

}
