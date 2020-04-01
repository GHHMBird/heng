package hhm.transfer.dao;

import java.sql.Connection;
import org.apache.commons.dbutils.QueryRunner;
import hhm.util.MyDataSourceUtils;

public class TransferDao {

	public int outMoney(Connection con, String out, double money) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "update account set money=money-? where name=?";
		
		int update = qr.update(sql, money, out);
		return update;
	}

	public int inMoney(Connection con, String in, double money) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "update account set money=money+? where name=?";
		
		int update = qr.update(sql, money, in);
		return update;
	}
	
	public int outMoney(String out, double money) throws Exception {
		Connection connection = MyDataSourceUtils.getConnection();
		QueryRunner qr = new QueryRunner();
		String sql = "update account set money=money-? where name=?";
		
		int update = qr.update(connection,sql, money, out);
		return update;
	}
	
	public int inMoney(String in, double money) throws Exception {
		Connection connection = MyDataSourceUtils.getConnection();
		QueryRunner qr = new QueryRunner();
		String sql = "update account set money=money+? where name=?";
		
		int update = qr.update(connection,sql, money, in);
		return update;
	}
}