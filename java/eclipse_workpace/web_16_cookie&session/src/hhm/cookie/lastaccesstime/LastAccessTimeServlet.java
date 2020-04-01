package hhm.cookie.lastaccesstime;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LastAccessTimeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String lastTime = null;
		
		Cookie[] cookies = request.getCookies();
		if (cookies!=null) {
			for (Cookie cook : cookies) {
				if ("time".equals(cook.getName())) {
					lastTime = cook.getValue();
				}
			}
		}
		
		response.setContentType("text/html;charset=utf-8");
		
		if (lastTime==null) {
			response.getWriter().write("您是第一次访问");
		}else {
			response.getWriter().write("您上次访问时间："+lastTime);
		}
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String currentTime = sdf.format(date);
		
		Cookie cookie = new Cookie("time", currentTime);
		cookie.setMaxAge(60*10);
		
		response.addCookie(cookie);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}