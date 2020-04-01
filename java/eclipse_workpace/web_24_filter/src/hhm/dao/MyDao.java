package hhm.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

import hhm.bean.domain.Account;
import hhm.utils.DataSourceUtils;

public class MyDao {

	public Account checkAccount(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username=? and password=?";
		Account account = qr.query(sql, new BeanHandler<Account>(Account.class),username,password);
		return account;
	}
	
}
