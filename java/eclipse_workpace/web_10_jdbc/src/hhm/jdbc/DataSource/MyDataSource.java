package hhm.jdbc.DataSource;

import java.awt.List;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class MyDataSource implements DataSource{
	
	//创建容器，存储connection对象
	private static LinkedList<Connection> list = new LinkedList<>();
	
	//创建五个链接放入容器
	static {
		for (int i = 0; i < 5; i++) {
			try {
				Connection connection = new hhm.jdbc.utils.UtilsJunit().before();
				MyConnection myConnection = new MyConnection(connection, list);
				list.add(myConnection);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		if (list.size()==0) {
			for (int i = 0; i < 5; i++) {
				try {
					connection = new hhm.jdbc.utils.UtilsJunit().before();
					MyConnection myConnection = new MyConnection(connection, list);
					list.add(myConnection);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//从池子中获取
		connection = list.remove(0);
		return connection;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
