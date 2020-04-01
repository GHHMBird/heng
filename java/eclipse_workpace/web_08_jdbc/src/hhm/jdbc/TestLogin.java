package hhm.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class TestLogin {
	
	@Test
	public void test() {
		try {
			//"a","a"  正常方法
			//"a' or 'a","随意输入"  sql注入
			login("a' or 'a","aaa");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//普通方法执行sql
	public void login(String uname,String upassword) throws Exception {
		//注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		//获取连接
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web08","root","www199346");
		//创建执行sql语句的对象
		Statement createStatement = connection.createStatement();
		//创建sql语句
		String sql = "select * from user where uname='"+uname+"' and upassword = '"+upassword+"'";
		//查询sql
		ResultSet executeQuery = createStatement.executeQuery(sql);
		if(executeQuery.next()) {
			System.out.println(uname+"登陆成功");
			System.out.println(sql);
		}else {
			System.out.println("账号或密码错误");
		}
		if (executeQuery!=null) {
			executeQuery.close();
		}
		if (createStatement!=null) {
			createStatement.close();
		}
		if (connection!=null) {
			connection.close();
		}
	}
	
	//预处理对象执行sql
	public void login2(String uname,String upassword) throws Exception {
		//注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		//获取连接
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web08","root","www199346");
		//创建sql语句
		String sql = "select * from user where uname=? and upassword = ?";
		//创建预处理对象
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		prepareStatement.setString(1, uname);
		prepareStatement.setString(2, upassword);
		ResultSet executeQuery = prepareStatement.executeQuery();
		if(executeQuery.next()) {
			System.out.println(uname+"登陆成功");
			System.out.println(sql);
		}else {
			System.out.println("账号或密码错误");
		}
		if (executeQuery!=null) {
			executeQuery.close();
		}
		if (prepareStatement!=null) {
			prepareStatement.close();
		}
		if (connection!=null) {
			connection.close();
		}
	}
}
