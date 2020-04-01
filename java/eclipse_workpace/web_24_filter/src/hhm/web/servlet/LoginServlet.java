package hhm.web.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hhm.bean.domain.Account;
import hhm.service.MyService;

public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		request.setCharacterEncoding("UTF-8");
		
		MyService service = new MyService();
		Account account = null;
		try {
			account = service.checkAccount(username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (account==null) {
			request.setAttribute("loginInfo", "用户名或密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else {
			
			String autoLogin = request.getParameter("autoLogin");
			if (autoLogin!=null&&!"".equals(autoLogin)) {
				
				String username_encode = URLEncoder.encode(username, "UTF-8");
				
				Cookie cookie_username = new Cookie("username", username_encode);
				Cookie cookie_password = new Cookie("password", account.getPassword());
				cookie_username.setMaxAge(60*60*1000);
				cookie_password.setMaxAge(60*60*1000);
				cookie_username.setPath(request.getContextPath());
				cookie_password.setPath(request.getContextPath());
				response.addCookie(cookie_username);
				response.addCookie(cookie_password);
				System.out.println("存起来了");
			}
			
			request.getSession().setAttribute("user", account);
			System.out.println(account.toString());
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}