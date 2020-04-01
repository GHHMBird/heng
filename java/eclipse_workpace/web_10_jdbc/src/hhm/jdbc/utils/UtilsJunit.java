package hhm.jdbc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UtilsJunit {
	
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet result;
	
	@Before
	public Connection before() throws Exception {
		ClassLoader classLoader = UtilsJunit.class.getClassLoader();
		InputStream stream = classLoader.getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(stream);
		String username = properties.getProperty("hhm.username");
		String password = properties.getProperty("hhm.password");
		String driver = properties.getProperty("hhm.driver");
		String mysql = properties.getProperty("hhm.mysql");
		Class.forName(driver);
		connection  = DriverManager.getConnection(mysql,username,password);
		return connection;
	}
	
	@Test
	public void test() throws Exception {
		statement = connection.prepareStatement("select * from product");
		result = statement.executeQuery();
		while(result.next()) {
			System.out.println(result.getString(2));
		}
	}
	
	@After
	public void after() throws Exception {
		if (result!=null) {
			result.close();
		}
		if (statement!=null) {
			statement.close();
		}
		if (connection!=null) {
			connection.close();
		}
	}
}
