package hhm.service;

import java.sql.SQLException;

import hhm.bean.domain.Account;
import hhm.dao.MyDao;

public class MyService {

	public Account checkAccount(String username, String password) throws SQLException {
		MyDao dao = new MyDao();
		return dao.checkAccount(username,password);
	}
	
}
