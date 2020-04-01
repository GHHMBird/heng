package hhm.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import hhm.bean.Product;
import hhm.service.MyService;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import net.sf.json.JSONArray;

public class SearchWordServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String word = request.getParameter("word");
		
		MyService service = new MyService();
		List<Product> list = null;
		try {
			list = service.searchWord(word);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JSONArray jsonArray = JSONArray.fromObject(list);
		
//		Gson gson = new Gson();
//		String json = gson.toJson(list);
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(jsonArray.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}