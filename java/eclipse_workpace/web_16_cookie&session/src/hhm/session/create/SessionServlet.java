package hhm.session.create;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建session对象
		HttpSession session = request.getSession();
		
		session.setAttribute("name", "Tom");
		
		//对应的JSESSIONID
		String id = session.getId();
		
		response.getWriter().write("id:"+id);
		
		Cookie cookie = new Cookie("JSESSIONID", id);
		
		//设置cookie的持续时间
		cookie.setMaxAge(60*60*24);
		
		cookie.setPath("/web_16_cookie&session/");
		
		response.addCookie(cookie);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}