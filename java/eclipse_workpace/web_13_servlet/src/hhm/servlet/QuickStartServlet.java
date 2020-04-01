package hhm.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.sun.org.apache.xml.internal.security.Init;

public class QuickStartServlet implements Servlet{
	
	@Override
	public void destroy() {
		System.out.println("QuickStartServlet destroy");
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	//默认第一次访问时调用，在xml配置  load-on-startup  即可在服务器启动时调用init方法
	@Override
	public void init(ServletConfig config) throws ServletException {
		String servletName = config.getServletName();//HHM
		//config.getInitParameter()//获取初始化参数
		System.out.println("QuickStartServlet init");
		
		//获得ServletContext
		ServletContext context = config.getServletContext();
		
		//在域中存数据等QuickStartServlet2取数据
		context.setAttribute("name", "hhm_name");
		context.setAttribute("count", 0);
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("QuickStartServlet running");
	}
	
}
