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
import hhm.service.ProductService;
import hhm.service.TypeService;

public class ProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductService();
		List<Product> list = null;
		try {
			list = service.getAllProduct();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		TypeService services = new TypeService();
		List<Category> lists = null;
		try {
			lists = services.getType();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("tList", lists);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}