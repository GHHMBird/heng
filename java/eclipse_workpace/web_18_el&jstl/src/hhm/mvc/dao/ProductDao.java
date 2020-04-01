package hhm.mvc.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import hhm.mvc.bean.Product;
import hhm.mvc.utils.DataSourceUtils;

public class ProductDao {
	public List<Product> getProducts() {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "select * from product";
			List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class));
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
