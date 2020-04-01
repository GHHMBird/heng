package hhm.jdbc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbcp.managed.BasicManagedDataSource;

public class DBCPUtils {
	
	private static DataSource dataSource;
	
	static {
		try {
		ClassLoader classLoader = DBCPUtils.class.getClassLoader();
		InputStream stream = classLoader.getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(stream);
		dataSource = BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
