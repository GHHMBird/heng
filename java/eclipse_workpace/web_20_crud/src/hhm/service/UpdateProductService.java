package hhm.service;

import java.sql.SQLException;

import hhm.bean.Product;
import hhm.dao.MyDao;

public class UpdateProductService {

	public void updateProduct(Product product) throws SQLException {
		MyDao dao = new MyDao();
		dao.updateProduct(product);
	}
	
}
