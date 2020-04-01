package hhm.service;

import java.sql.SQLException;

import hhm.dao.MyDao;

public class DeleteService {

	public void deleteProduct(String pid) throws SQLException {
		MyDao dao = new MyDao();
		dao.deleteProduct(pid);
	}
	
}
