package hhm.listener;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import hhm.domain.User;
import hhm.email.DataSourceUtils;
import hhm.email.MailUtils;

public class BirthdayListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0)  { 
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				System.out.println("begin");
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
				Date date = new Date();
				String currentDate = sdf.format(date);
				
				QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
				String sql = "select * from user where birthday like ?";
				List<User> list = null;
				try {
					list = qr.query(sql, new BeanListHandler<User>(User.class),"%"+currentDate+"%");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				System.out.println("finish");
				System.out.println(list.size());
				
				if (list!=null&&list.size()>0) {
					for (User user : list) {
						try {
							MailUtils.sendMail(user.getEmail(), "生日快乐", "亲爱的"+user.getRealname()+"!!!Fuck off");
						} catch (AddressException e) {
							e.printStackTrace();
						} catch (MessagingException e) {
							e.printStackTrace();
						}
					}
				}
				
			}
		}, new Date(), 60*1000);
    }
	
}