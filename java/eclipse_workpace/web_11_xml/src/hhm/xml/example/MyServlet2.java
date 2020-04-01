package hhm.xml.example;

public class MyServlet2 implements MyServletInterface{

	@Override
	public void init() {
		System.out.println("MyServlet2-init");
	}

	@Override
	public void service() {
		System.out.println("MyServlet2-service");
	}

	@Override
	public void destory() {
		System.out.println("MyServlet2-destory");
	}
	
}
