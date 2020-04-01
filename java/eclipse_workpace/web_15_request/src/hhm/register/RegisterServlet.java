package hhm.register;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;

import hhm.utils.DataSourceUtils;

public class RegisterServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//get,post请求处理乱码 utf8编码  ISO88591解码
			String username = request.getParameter("username");
			username = new String(username.getBytes("iso8859-1"),"UTF-8");
			
			//System.out.println(username);
			
			//post请求处理乱码
			//request.setCharacterEncoding("UTF-8");
			
			//获取数据(使用beanutils直接封装到bean中)
			Map<String, String[]> map = request.getParameterMap();
			
			//将散装数据 封装到bean中
			User user = new User();
			BeanUtils.populate(user, map);
			
			user.setUsername(username);
			
			//手动封装uid		--->	uuid  随机的不重复的字符串(32位)  java代码生成后是36位
			user.setUid(UUID.randomUUID().toString());
			
			//将参数传递给一个业务操作方法
			register(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//注册成功跳登录页面
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/*
	 * 封装一个业务操作方法
	 */
	public void register(User user) throws Exception {
		QueryRunner qRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		qRunner.update(sql,user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),null,null);
	}
}