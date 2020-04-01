package hhm.service;

import java.sql.SQLException;
import java.util.List;

import hhm.bean.Product;
import hhm.dao.MyDao;

public class MyService {

	public boolean checkUsername(String username) throws SQLException {
		MyDao dao = new MyDao();
		return dao.checkUsername(username);
	}

	public List<Product> searchWord(String word) throws SQLException {
		MyDao dao = new MyDao();
		return dao.searchWord(word);
	}
	
}
