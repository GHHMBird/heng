package hhm.product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class ProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//准备所有商品数据集合
		try {
			QueryRunner qRunner = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "select * from product";
		
			List<Product> list = qRunner.query(sql,new BeanListHandler<Product>(Product.class));
			
			//把数据存到request中转给jsp
			request.setAttribute("list", list);
			request.getRequestDispatcher("/product_list.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}