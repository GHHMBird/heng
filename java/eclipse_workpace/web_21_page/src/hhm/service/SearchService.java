package hhm.service;

import java.sql.SQLException;
import java.util.List;
import hhm.bean.Product;
import hhm.dao.MyDao;
import hhm.vo.SearchVo;

public class SearchService {

	public Product getProductByPid(String pid) throws SQLException {
		MyDao dao = new MyDao();
		return dao.getProductByPid(pid);
	}

	public List<Product> searchSomeVo(SearchVo vo) throws SQLException {
		MyDao dao = new MyDao();
		return dao.searchSomeVo(vo);
	}

}
