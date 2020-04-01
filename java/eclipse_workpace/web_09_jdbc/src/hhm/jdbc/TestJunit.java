package hhm.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestJunit {
	
	private Connection connection;
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;
	
	@Before
	public void before() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/web09";
		connection  = DriverManager.getConnection(url,"root","www199346");
	}
	
	@Test
	public void test() throws Exception {
		String sql = "select * from product where category_id=?";
		prepareStatement = connection.prepareStatement(sql);
		prepareStatement.setString(1, "c001");
		resultSet = prepareStatement.executeQuery();
		while (resultSet.next()) {
			System.out.println(resultSet.getString(1));//打出来了pid
		}
	}
	
	@After
	public void after() throws Exception {
		if (resultSet!=null) {
			resultSet.close();
		}
		if (prepareStatement!=null) {
			prepareStatement.close();
		}
		if (connection!=null) {
			connection.close();
		}
	}
}
