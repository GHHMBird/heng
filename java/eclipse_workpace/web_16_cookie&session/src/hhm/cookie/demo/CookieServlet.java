package hhm.cookie.demo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie cookie = new Cookie("name", "zhangsan");
		
		//为cookie设置持久化时间，他会保存在磁盘中，而不仅仅保存在内存中
		cookie.setMaxAge(60*10);//单位是秒       创建同名cookie设置时间为0可删除cookie
		
		//为cookie设置携带的路径，当访问该路径才会带cookie
		//cookie.setPath("/");//当前servlet所在的/下
		cookie.setPath("/web_16_cookie&session");//当前项目下
//		cookie.setPath("/web_16_cookie&session/index.html");
		
		response.addCookie(cookie);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}