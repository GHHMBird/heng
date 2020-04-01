package hhm.demo;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DemoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置response响应行的状态码
		response.setStatus(302);//默认200
		response.setHeader("location", "/web_14_response/servlet2");
		
		response.getWriter().write("俺没有资源");
		
		//添加响应头
		response.addHeader("name", "hhm");
		response.addHeader("name", "hhm");
		response.addIntHeader("age", 255);
		response.addDateHeader("nowTime", new Date().getTime());
		
		response.setIntHeader("age", 666666);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}