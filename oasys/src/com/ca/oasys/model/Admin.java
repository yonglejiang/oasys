package com.ca.oasys.model;

import java.util.Date;
public class Admin {

	private int aid;
	private String account;
	private String password;
	private int eno;// 关联Employer类中的eno
	private String email;
	private int phone;
	private String logip;
	private Date loginTime;
	private String privilage;// 权限列表,多个权限之间用逗号分隔

	public Admin() {
		super();
	}

	public Admin(String account, String password, int eno, String email, int phone, String logip, Date loginTime,
			String privilage) {
		super();
		this.account = account;
		this.password = password;
		this.eno = eno;
		this.email = email;
		this.phone = phone;
		this.logip = logip;
		this.loginTime = loginTime;
		this.privilage = privilage;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEno() {
		return eno;
	}

	public void setEno(int eno) {
		this.eno = eno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getLogip() {
		return logip;
	}

	public void setLogip(String logip) {
		this.logip = logip;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getPrivilage() {
		return privilage;
	}

	public void setPrivilage(String privilage) {
		this.privilage = privilage;
	}
	
	
	

}
