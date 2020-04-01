package hhm.service;

import java.sql.SQLException;

import hhm.bean.Product;
import hhm.dao.MyDao;

public class SearchService {

	public Product getProductByPid(String pid) throws SQLException {
		MyDao dao = new MyDao();
		return dao.getProductByPid(pid);
	}

}
