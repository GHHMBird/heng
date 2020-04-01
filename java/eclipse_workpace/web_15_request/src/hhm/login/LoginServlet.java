package hhm.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

import hhm.register.User;
import hhm.utils.DataSourceUtils;

public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			QueryRunner qRunner = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "select * from user where username=? and password=?";
			
			User user = qRunner.query(sql, new BeanHandler<User>(User.class) ,username,password);
			if (user==null) {
				request.setAttribute("loginInfo", "用户名或密码错误");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}