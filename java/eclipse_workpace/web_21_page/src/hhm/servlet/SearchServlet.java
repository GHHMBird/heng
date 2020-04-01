package hhm.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hhm.bean.Category;
import hhm.bean.Product;
import hhm.service.SearchService;
import hhm.service.TypeService;

public class SearchServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		TypeService tService = new TypeService();
		SearchService Sservice = new SearchService();
		List<Category> clist = null;
		Product product = null;
		try {
			clist = tService.getType();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			product = Sservice.getProductByPid(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("clist", clist);
		request.setAttribute("product", product);
		
		request.getRequestDispatcher("/admin/product/edit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}