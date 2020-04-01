package hhm.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent event)  { 
    	String id = event.getSession().getId();
    	System.out.println("HttpSession创建啦!!!  id=" + id);
    }

    public void sessionDestroyed(HttpSessionEvent event)  { 
    	System.out.println("HttpSession销毁啦!!!");
    }
	
}