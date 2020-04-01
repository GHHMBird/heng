package hhm.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hhm.bean.Product;
import hhm.service.ProductListService;
import hhm.vo.PageBean;

public class ProductPageListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductListService pls = new ProductListService();
		int currentPage = 1;
		int currentCount = 12;
		
		String page = request.getParameter("currentPage");
		if (page!=null&&!page.equals("")) {
			currentPage=Integer.parseInt(page);
		}
		
		PageBean bean = null;
		try {
			bean = pls.getProductPageBean(currentPage,currentCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("bean", bean);
		
		System.out.println(bean);
		
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}