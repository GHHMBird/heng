package hhm.transfer.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hhm.transfer.service.TransferService;

public class TransferServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String out = request.getParameter("out");
		String in = request.getParameter("in");
		String moneyStr = request.getParameter("money");
		double money = Double.parseDouble(moneyStr);
		
		//调用service层转账方法
		TransferService service = new TransferService();
		boolean isSuccess = service.transfer(out,in,money);
		
		response.setContentType("text/html;charset=utf-8");
		if (isSuccess) {
			response.getWriter().write("转账成功");
		}else {
			response.getWriter().write("转账失败");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}