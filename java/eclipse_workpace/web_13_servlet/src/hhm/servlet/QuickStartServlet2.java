package hhm.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTML;
import javax.xml.transform.sax.SAXResult;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

import hhm.bean.User;
import hhm.utils.DataSourceUtils;

public class QuickStartServlet2 extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		ServletContext context = config.getServletContext();
		
		//获取初始配置
		String string = context.getInitParameter("web.xml.hhm.init");
		System.out.println(string);
				
		//获取绝对路径
		String realPath_a = context.getRealPath("a.txt");
		System.out.println("a.txt路径:    "+realPath_a);
		
		String realPath_b = context.getRealPath("WEB-INF/classes/b.txt");
		System.out.println("b.txt路径:    "+realPath_b);
		
		//classLoader 专门读取src下的class文件
		String realPath_b2 = QuickStartServlet2.class.getClassLoader().getResource("b.txt").getPath();
		System.out.println("b.txt路径:    "+realPath_b2);
				
//		String realPath_c = context.getRealPath("c文件");//c文件获取不到
//		System.out.println(realPath_c);
				
		String realPath_d = context.getRealPath("WEB-INF/d.txt");
		System.out.println("d.txt路径:    "+realPath_d);
		
		//从域中取QuickStartServlet存的数据
		String attribute = (String) context.getAttribute("name");
		System.out.println(attribute);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
//		ResourceBundle bundle = ResourceBundle.getBundle("db");
//		String username = bundle.getString("hhm.username");
//		String password = bundle.getString("hhm.password");
//		String driver = bundle.getString("hhm.driver");
//		String mysql = bundle.getString("hhm.mysql");
		
//		ClassLoader loader = QuickStartServlet2.class.getClassLoader();
//		InputStream inputStream = loader.getResourceAsStream("db.properties");
//		Properties properties = new Properties();
//		properties.load(inputStream);
//		String root = properties.getProperty("hhm.username");
//		String pass = properties.getProperty("hhm.password");
//		String driver = properties.getProperty("hhm.driver");
//		String mysql = properties.getProperty("hhm.mysql");	
		
		try {
			String sql = "select * from user where username=? and password=?";
			QueryRunner qRunner = new QueryRunner(DataSourceUtils.getDataSource());
			User user = qRunner.query(sql, new BeanHandler<User>(User.class), username, password);
			if (user==null) {
				response.getWriter().write("error");
			}else {
				int count = (int) getServletContext().getAttribute("count");
				count++;
				response.getWriter().write("success,email:"+user.getEmail() + "				you are login people :" + count );
				getServletContext().setAttribute("count", count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}