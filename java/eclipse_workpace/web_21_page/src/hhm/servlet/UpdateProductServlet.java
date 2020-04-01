package hhm.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import hhm.bean.Product;
import hhm.service.UpdateProductService;

public class UpdateProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> map = request.getParameterMap();
		Product product = new Product();
		try {
			BeanUtils.populate(product, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		product.setPname(new String(product.getPname().getBytes(),"utf-8"));
		
		System.out.println(product.getPname());
		
		product.setPimage("product/1/c_0033.jpg");
		product.setPdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		product.setPflag("0");
		
		UpdateProductService service = new UpdateProductService();
		try {
			service.updateProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/product");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}