package hhm.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//验证码校验
		request.setCharacterEncoding("UTF-8");
		
		String yanzhengma = request.getParameter("yanzhengma");
		
		String word = (String) request.getSession().getAttribute("checkcode_session");
		
		if (word.equals(yanzhengma)) {
			//验证账号密码
			
		}else {
			//返回失败
			request.setAttribute("loginInfo", "验证码输入错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}