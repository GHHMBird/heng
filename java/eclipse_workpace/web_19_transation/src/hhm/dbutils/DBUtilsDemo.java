package hhm.dbutils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import hhm.util.DataSourceUtils;

public class DBUtilsDemo {
	public static void main(String[] args) {
		Connection connection = null;
		try {
			connection = DataSourceUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			
			//开启事务
			connection.setAutoCommit(false);
			
			String sql = "update account set money=10000 where name=zhangsan";
			int update = runner.update(connection,sql);
			
			//提交或回滚
			if (update>0) {
				connection.commit();
			}else {
				connection.rollback();
			}
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
