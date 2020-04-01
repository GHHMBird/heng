package hhm.demo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HtmlServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		response.getWriter().write("<!DOCTYPE html>");
		response.getWriter().write("<html>");
		response.getWriter().write("<head>");
		response.getWriter().write("<meta charset=\'UTF-8\'>");
		response.getWriter().write("<title>Insert title here</title>");
		response.getWriter().write("</head>");
		response.getWriter().write("<body>");
		response.getWriter().write("<h1>写的真恶心，真是日了狗</h1>");
		response.getWriter().write("</body>");
		response.getWriter().write("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}