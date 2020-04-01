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

import com.sun.org.apache.bcel.internal.generic.DDIV;
import com.sun.org.apache.bcel.internal.generic.NEW;

import hhm.bean.Product;
import hhm.service.AddProductService;

public class AddProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String pname = request.getParameter("pname");
//		String is_hot = request.getParameter("is_hot");
//		String market_price = request.getParameter("market_price");
//		String shop_price = request.getParameter("shop_price");
//		String cid = request.getParameter("categorySecond.csid");
//		String pdesc = request.getParameter("pdesc");
		
		Map<String, String[]> map = request.getParameterMap();
		Product product = new Product();
		try {
			BeanUtils.populate(product, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		product.setPid(UUID.randomUUID().toString());
		product.setPimage("product/1/c_0033.jpg");
		product.setPdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		product.setPflag("0");
		
		AddProductService service = new AddProductService();
		try {
			service.addProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/product");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}