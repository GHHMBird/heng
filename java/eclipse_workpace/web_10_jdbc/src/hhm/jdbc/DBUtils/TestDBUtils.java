package hhm.jdbc.DBUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import hhm.jdbc.bean.User;
import hhm.jdbc.utils.C3P0Utils;

public class TestDBUtils {
	
	//增
	@Test
	public void testAddUser() {
		try {
			QueryRunner qRunner = new QueryRunner(C3P0Utils.getDataSource());
			User user = new User();
			String sql = "insert into product values(?,?,?,?)";
			Object[] objects = {"p011","0","0","c004"};
			int nums = qRunner.update(sql,objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//改
	@Test
	public void testUpDateUser() {
		try {
			QueryRunner qRunner = new QueryRunner(C3P0Utils.getDataSource());
			User user = new User();
			String sql = "update product set price=? where pname=?";
			Object[] objects = {"100","0"};
			int nums = qRunner.update(sql,objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//删
	@Test
	public void testDeleteUser() {
		try {
			QueryRunner qRunner = new QueryRunner(C3P0Utils.getDataSource());
			User user = new User();
			String sql = "delete from product where pname=?";
			Object[] objects = {"0"};
			int nums = qRunner.update(sql,objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//查
	@Test
	public void testSelectUser() {
		try {
			QueryRunner qRunner = new QueryRunner(C3P0Utils.getDataSource());
			String sql = "select * from product";
			List<User> list = qRunner.query(sql, new BeanListHandler<User>(User.class));
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getPid());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//查
	@Test
	public void testSelectUserById() {
		try {
			QueryRunner qRunner = new QueryRunner(C3P0Utils.getDataSource());
			String sql = "select * from product where pname=?";
			Object[] objects = {"0"};
			User user = qRunner.query(sql, new BeanHandler<User>(User.class),objects);
				System.out.println(user.getPid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//查
	@Test
	public void testSelectUserCount() {
		try {
			QueryRunner qRunner = new QueryRunner(C3P0Utils.getDataSource());
			String sql = "select count(*) from product";
			Long count = (Long) qRunner.query(sql, new ScalarHandler());
			System.out.println(count+"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//查
	@Test
	public void testSelectUserAll() {
		try {
			QueryRunner qRunner = new QueryRunner(C3P0Utils.getDataSource());
			String sql = "select * from product";
			List<Map<String, Object>> query = qRunner.query(sql, new MapListHandler());
			for (Map<String, Object> map : query) {
				System.out.println(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//查
	@Test
	public void testSelectUserAll2() {
		try {
			QueryRunner qRunner = new QueryRunner(C3P0Utils.getDataSource());
			String sql = "select * from product";
			List<Object> query = qRunner.query(sql, new ColumnListHandler("pname"));
			for (Object object : query) {
				System.out.println(object);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
