package hhm.service;

import java.sql.SQLException;
import java.util.List;

import hhm.bean.Category;
import hhm.dao.MyDao;
import javafx.scene.chart.PieChart.Data;

public class TypeService {

	public List<Category> getType() throws SQLException {
		MyDao dao = new MyDao();
		return dao.getType();
	}
	
}
