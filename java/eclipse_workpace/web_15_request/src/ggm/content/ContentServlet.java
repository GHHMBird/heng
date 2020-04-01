package ggm.content;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContentServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String[] hobbys = request.getParameterValues("hobby");
		
		response.setContentType("text/plain;charaset=UTF-8");
		
		String text = "username:"+username+"        password:"+password+"        ";
		
		for (int i = 0; i < hobbys.length; i++) {
			text += "[" + hobbys[i] + "]";
		}
		
		Map<String, String[]> map = request.getParameterMap();
		for (Map.Entry<String, String[]> string : map.entrySet()) {
			System.out.println(string.getKey());
			for (String str : string.getValue()) {
				System.out.println(str);
			}
		}
		
		response.getWriter().write(text);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}