package hhm.jdbc_properties_stream;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestJunit {
	
	private Connection connection;
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;
	
	@Before
	public void Before() throws Exception {
		ClassLoader classLoader = TestJunit.class.getClassLoader();
		InputStream stream = classLoader.getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(stream);
		String username = properties.getProperty("hhm.jdbc.username");
		String password = properties.getProperty("hhm.jdbc.password");
		String driver = properties.getProperty("hhm.jdbc.driver");
		String url = properties.getProperty("hhm.jdbc.url");
		Class.forName(driver);
		connection = DriverManager.getConnection(url,username,password);
	}
	
	@Test
	public void testDelete() throws Exception {
		String sql = "delete from product where pid = ?";
		prepareStatement = connection.prepareStatement(sql);
		prepareStatement.setString(1, "p011");
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
