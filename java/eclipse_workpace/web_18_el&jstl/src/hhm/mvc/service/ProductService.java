package hhm.mvc.service;

import java.util.List;

import hhm.mvc.bean.Product;
import hhm.mvc.dao.ProductDao;

public class ProductService {
	public List<Product> findAllProduct() {
		//处理业务，传递请求到DAO层
		ProductDao pdao = new ProductDao();
		List<Product> list = pdao.getProducts();
		return list;
	}
}
