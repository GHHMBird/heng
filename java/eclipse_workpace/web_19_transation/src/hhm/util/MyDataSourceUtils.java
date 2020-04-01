package hhm.util;

import java.sql.Connection;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MyDataSourceUtils {

	//从连接池中获取Connection
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	//ThreadLocal是一个map  他的key是当前线程的名字，value可存放任何值，当作list用即可
	private static ThreadLocal<Connection> tl = new ThreadLocal<>();
	
	public static Connection getCurrentConnection() throws Exception {
		return dataSource.getConnection();//每次都创建新的
	}
	
	public static Connection getConnection() throws Exception {
		Connection con = tl.get();
		if (con==null) {
			con = getCurrentConnection();
			tl.set(con);
		}
		return con;
	}
	
	public static void startTransaction() throws Exception {
		getConnection().setAutoCommit(false);
	}
	
	public static void commit() throws Exception {
		getConnection().commit();
	}
	
	public static void rollBack() throws Exception {
		getConnection().rollback();
	}
}
