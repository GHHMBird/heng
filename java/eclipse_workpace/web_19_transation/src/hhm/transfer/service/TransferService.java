package hhm.transfer.service;

import java.sql.Connection;
import java.sql.SQLException;
import hhm.transfer.dao.TransferDao;
import hhm.util.DataSourceUtils;

public class TransferService {

	public boolean transfer(String out, String in, double money) {
		TransferDao dao = new TransferDao();
		boolean isSuccess = true;
		Connection con = null;
		
		try {
			//开启事务
//			MyDataSourceUtils.startTransaction();
			con = DataSourceUtils.getConnection();
			con.setAutoCommit(false);
			
			//从out扣款
			dao.outMoney(con,out,money);
			
			//往in加款
			dao.inMoney(con,in,money);
			
		} catch (Exception e) {
			isSuccess = false;
			try {
				//回滚事务
				//MyDataSourceUtils.rollBack();
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				//提交事务
				//MyDataSourceUtils.commit();
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isSuccess;
	}
}