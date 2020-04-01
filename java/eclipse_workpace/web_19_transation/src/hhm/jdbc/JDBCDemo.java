package hhm.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.junit.Test;

public class JDBCDemo {
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql:///web19","root","www199346");
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("insert into account values(null,'zhangsan',3000)");
		
		stmt.close();
		connection.close();
	}
	
	@Test
	public void test() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql:///web19","root","www199346");
		
		//设置是否自动提交事务
		connection.setAutoCommit(false);
		
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("insert into account values(null,'lisi',300)");
		
		connection.commit();
		
		stmt.close();
		connection.close();
	}
	
	@Test
	public void testCommit() {
		Connection connection = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql:///web19","root","www199346");
			
			//设置是否自动提交事务
			connection.setAutoCommit(false);
			
			stmt = connection.createStatement();
			int executeUpdate = stmt.executeUpdate("insert into account values(null,'wangwu',300)");
			
			if (executeUpdate>0) {
				connection.commit();
			}else {
				connection.rollback();
			}
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
