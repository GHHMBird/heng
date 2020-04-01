package hhm.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

import hhm.bean.Category;
import hhm.bean.Product;
import hhm.utils.DataSourceUtils;
import hhm.vo.SearchVo;

public class MyDao {

	public List<Product> getAllProduct() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}

	public List<Category> getType() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category";
		List<Category> list = qr.query(sql, new BeanListHandler<Category>(Category.class));
		return list;
	}

	public void addProduct(Product p) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql,p.getPid(),p.getPname(),p.getMarket_price(),p.getShop_price(),p.getPimage(),p.getPdate(),p.getIs_hot(),p.getPdesc(),p.getPflag(),p.getCid());
	}

	public void deleteProduct(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from product where pid=?";
		qr.update(sql, pid);
	}

	public Product getProductByPid(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid=?";
		Product product = qr.query(sql, new BeanHandler<Product>(Product.class), pid);
		return product;
	}

	public void updateProduct(Product p) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update product set pname=?,market_price=?,shop_price=?,is_hot=?,pimage=?,pdate=?,pdesc=?,pflag=?,cid=? where pid=?";
		qr.update(sql,p.getPname(),p.getMarket_price(),p.getShop_price(),p.getIs_hot(),p.getPimage(),p.getPdate(),p.getPdesc(),p.getPflag(),p.getCid(),p.getPid());
	}

	public List<Product> searchSomeVo(SearchVo vo) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where 1=1 ";
		ArrayList<Object> list = new ArrayList<>();
		if (vo.getPname()!=null&&!vo.getPname().trim().equals("")) {
			sql += " and pname like ? ";
			list.add("%" + vo.getPname().trim() + "%");
		}
		if (!vo.getIs_hot().equals("2")) {
			sql += " and is_hot=? ";
			list.add(vo.getIs_hot());
		}
		if (vo.getCid()!=null&&!vo.getCid().trim().equals("")) {
			sql += " and cid=? ";
			list.add(vo.getCid());
		}
		List<Product> query = qr.query(sql, new BeanListHandler<Product>(Product.class),list.toArray());
		return query;
	}

	public List<Product> getProductList() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}

	public Long getAllProductCount() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product";
		Long count = (Long) qr.query(sql, new ScalarHandler());
		return count;
	}

	public List<Product> getPageBeanProduct(int currentPage, int currentCount) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product limit ?,?";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class),(currentPage-1)*currentCount,currentCount);
		return list;
	}
	
}