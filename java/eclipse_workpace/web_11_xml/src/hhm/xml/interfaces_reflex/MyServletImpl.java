package hhm.xml.interfaces_reflex;

public class MyServletImpl implements MyServletInterface{

	@Override
	public void init() {
		System.out.println("init");
	}

	@Override
	public void service() {
		System.out.println("service");
	}

	@Override
	public void destory() {
		System.out.println("destory");
	}
}
