package hhm.listener;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		ServletContext source = (ServletContext) event.getSource();//这个source方法获得的对象也是ServletContext
		System.out.println("ServletContext销毁啦！！！");
		
		new Timer().scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				System.out.println("银行发钱啦!!!");
			}
		}, new Date(), 5000);
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("ServletContext创建啦！！！");
	}

}