package hhm.service;

import java.sql.SQLException;
import java.util.List;
import hhm.bean.Product;
import hhm.dao.MyDao;

public class ProductService {

	public List<Product> getAllProduct() throws SQLException {
		MyDao dao = new MyDao();
		List<Product> list = dao.getAllProduct();
		return list;
	}
}
