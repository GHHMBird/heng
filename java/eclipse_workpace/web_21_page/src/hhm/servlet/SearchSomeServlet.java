package hhm.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import hhm.bean.Category;
import hhm.bean.Product;
import hhm.service.SearchService;
import hhm.service.TypeService;
import hhm.vo.SearchVo;

public class SearchSomeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Map<String, String[]> map = request.getParameterMap();
		SearchVo vo = new SearchVo();
		try {
			BeanUtils.populate(vo, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		SearchService service = new SearchService();
		List<Product> list = null;
		try {
			list = service.searchSomeVo(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		TypeService service2 = new TypeService();
		List<Category> list2 = null;
		try {
			list2 = service2.getType();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("tList", list2);
		
		request.setAttribute("vo", vo);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}