package hhm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//无法解决get提交的中文乱码
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		System.out.println(name);
		
		response.setContentType("text/html;charset=UTF-8");
		
//		response.getWriter().write("zhangsan");
//		response.getWriter().write(Math.random()+"");
		response.getWriter().write("{\"name\":\"张三\",\"password\":\"www199346\"}");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}