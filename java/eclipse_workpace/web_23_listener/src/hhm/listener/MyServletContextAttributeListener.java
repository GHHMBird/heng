package hhm.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class MyServletContextAttributeListener implements ServletContextAttributeListener {

    public void attributeAdded(ServletContextAttributeEvent event)  {
    	String name = event.getName();
    	Object value = event.getValue();
        System.out.println("ServletContext添加了!!!      name="+name+";value="+value.toString());
    }

    public void attributeRemoved(ServletContextAttributeEvent event)  { 
    	String name = event.getName();
    	Object value = event.getValue();
    	System.out.println("ServletContext移除了!!!      name="+name+";value="+value.toString());
    }

    public void attributeReplaced(ServletContextAttributeEvent event)  { 
    	String name = event.getName();
    	Object value = event.getValue();
    	System.out.println("ServletContext更新了!!!      name="+name+";value="+value.toString());
    }
	
}