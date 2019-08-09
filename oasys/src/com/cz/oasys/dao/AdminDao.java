package com.cz.oasys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ca.oasys.model.Admin;
import com.cz.oasys.jdbc.DbHelp;
import com.mysql.jdbc.Connection;

public class AdminDao {

	private DbHelp cp = null;
	private Connection conn = null;
	private String addSql = "insert into admin values(null,?,?,?,?,?,?,?,?)";
	private String deleteSql = "delete from admin where id=?";
	private String updateSql = "update admin set account=?,password=?,eno=?,email=?,phone=?,logip=?,logintime=?,privilage=? where aid=?";
	private String getOneByID = "select * from admin where aid =?";
	private String getAll = "select * from admin";
	private String getOneByAccount = "select * from admin where account =?";

	public AdminDao(DbHelp cp) {
		this.cp=cp;
	}

	// 根据account获得一个Admin
	public Admin getOneByAccount(String account) {
		Admin admin = new Admin();
		try {
			conn = cp.getConnection();
			PreparedStatement ps = conn.prepareStatement(getOneByAccount);
			ps.setString(1, account);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				admin.setAccount(rs.getString("account"));
				admin.setPassword(rs.getString("password"));
				admin.setEno(rs.getInt("eno"));
				admin.setEmail(rs.getString("email"));
				admin.setPhone(rs.getInt("phone"));
				admin.setLogip(rs.getString("logip"));
				admin.setLoginTime(rs.getDate("logintime"));
				admin.setPrivilage(rs.getString("privilage"));
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println("获取信息失败" + e.getMessage());
		} finally {
			cp.closeConn(conn);
		}
		return admin;
	}

}
