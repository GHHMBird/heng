package hhm.jdbc_properties_resourcebundle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestJunit {
	
	private Connection connection;
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;
	
	@Before
	public void Before() throws Exception {
		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
		String username = bundle.getString("hhm.jdbc.username");
		String password = bundle.getString("hhm.jdbc.password");
		String driver = bundle.getString("hhm.jdbc.driver");
		String url = bundle.getString("hhm.jdbc.url");
		Class.forName(driver);
		connection = DriverManager.getConnection(url,username,password);
	}
	
	@Test
	public void testSelect() throws Exception {
		String sql = "select * from product where pid = ?";
		prepareStatement = connection.prepareStatement(sql);
		prepareStatement.setString(1, "p001");
		resultSet = prepareStatement.executeQuery();
		while (resultSet.next()) {
			System.out.println(resultSet.getString(2));
		}
	}
	
	@Test
	public void testInsert() throws Exception {
		String sql = "insert into product values(?,?,?,?)";
		 prepareStatement = connection.prepareStatement(sql);
		 prepareStatement.setString(1, "p011");
		 prepareStatement.setString(2, "abc");
		 prepareStatement.setDouble(3, 2.01);
		 prepareStatement.setString(4, "c004");
		 prepareStatement.execute();
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
