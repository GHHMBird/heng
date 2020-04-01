package hhm.service;

import java.sql.SQLException;

import hhm.bean.Product;
import hhm.dao.MyDao;

public class AddProductService {

	public void addProduct(Product product) throws SQLException {
		MyDao myDao = new MyDao();
		myDao.addProduct(product);
	}
	
}
