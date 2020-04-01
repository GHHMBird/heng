package hhm.vo;

import java.util.ArrayList;
import java.util.List;

import hhm.bean.Product;

public class PageBean {
	@Override
	public String toString() {
		return "PageBean [list=" + list + ", totalPage=" + totalPage + ", currentPage=" + currentPage + ", totalCount="
				+ totalCount + ", currentCount=" + currentCount + "]";
	}
	public List<Product> getList() {
		return list;
	}
	public void setList(List<Product> list) {
		this.list = list;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	private List<Product> list = new ArrayList<>();
	private int totalPage;
	private int currentPage;
	private int totalCount;
	private int currentCount;
}
