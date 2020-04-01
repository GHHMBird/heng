package hhm.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import hhm.bean.Product;
import hhm.utils.DataSourceUtils;

public class MyDao {

	public boolean checkUsername(String username) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from user where username=?";
		Long count = (Long) qr.query(sql,new ScalarHandler(),username);
		if (count==0) {
			return false;
		}else {
			return true;
		}
	}

	public List<Product> searchWord(String word) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pname like ? limit 0,8";
		List<Product> list = qr.query(sql,new BeanListHandler<Product>(Product.class),"%"+word+"%");
		return list;
	}

}
