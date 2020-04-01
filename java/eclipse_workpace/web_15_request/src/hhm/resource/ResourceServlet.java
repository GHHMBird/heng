package hhm.resource;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResourceServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//请求行和其他的一些信息
		String method = request.getMethod();
		StringBuffer requestURL = request.getRequestURL();
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String queryString = request.getQueryString();
		String remoteAddr = request.getRemoteAddr();
		
		//请求头的信息
		String agent = request.getHeader("User-Agent");
		Enumeration<String> headerNames = request.getHeaderNames();
		
		
		response.setContentType("text/html;charset=UTF-8");
		
		String text = "method:"+method+"        "+"requestURI:"+requestURI+"        "+"requestURL:"+requestURL.toString()+"        "+"contextPath:"+contextPath+"        "+"queryString:"+queryString+"        "+"您的ip地址是"+remoteAddr;
		
		text += "        "+"User-Agent:"+ agent+"        ";
		
		while (headerNames.hasMoreElements()) {
			String string = (String) headerNames.nextElement();
			text += "[(" + string + ")" + "(" + request.getHeader(string) + ")" + "]";
		}
		
		response.getWriter().write(text);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}