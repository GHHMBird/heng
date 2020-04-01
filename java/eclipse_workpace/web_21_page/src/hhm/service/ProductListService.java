package hhm.service;

import java.sql.SQLException;
import java.util.List;

import hhm.bean.Product;
import hhm.dao.MyDao;
import hhm.vo.PageBean;

public class ProductListService {

	public List<Product> getProduct() throws SQLException {
		MyDao dao = new MyDao();
		return dao.getProductList();
	}

	public PageBean getProductPageBean(int currentPage, int currentCount) throws SQLException {
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentCount);
		
		MyDao dao = new MyDao();
		
		Long count = dao.getAllProductCount();
		pageBean.setTotalCount(count.intValue());
		
		if (count.intValue()%currentCount==0) {
			pageBean.setTotalPage((int)(count.intValue()/currentCount));
		}else {
			pageBean.setTotalPage((int)(count.intValue()/currentCount+1));
		}
		
		List<Product> list = dao.getPageBeanProduct(currentPage,currentCount);
		pageBean.setList(list);
		
		return pageBean;
	}

}
