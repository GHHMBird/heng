package hhm.web.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import hhm.bean.domain.Account;
import hhm.service.MyService;

public class LoginFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("过滤到了");
		String cookie_name = null;
		String cookie_password = null;
		
		//获得cookie
		HttpServletRequest req = (HttpServletRequest)request;
		Cookie[] cookies = req.getCookies();
		if (cookies!=null) {
			for(Cookie cookie:cookies) {
				if ("username".equals(cookie.getName())) {
					cookie_name = URLDecoder.decode(cookie.getValue(),"UTF-8");
				}
				if ("password".equals(cookie.getName())) {
					cookie_password = cookie.getValue();
				}
			}
		}
		
		System.out.println(cookie_name);
		System.out.println(cookie_password);
		
		if (cookie_name!=null&&cookie_password!=null) {
			MyService service = new MyService();
			Account account = null;
			try {
				account = service.checkAccount(cookie_name,cookie_password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			req.getSession().setAttribute("user", account);
			
			System.out.println(account.toString());
		}
		
		System.out.println("过滤完成");
		
		chain.doFilter(req, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}