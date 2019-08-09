package com.cz.oasys.factory;

import com.cz.oasys.dao.AdminDao;
import com.cz.oasys.jdbc.DbHelp;

public class DaoFactory {
	//·µ»ŘAdminDao
	public static AdminDao getAdminDao(DbHelp cp){
		return new AdminDao(cp);
	}

}
