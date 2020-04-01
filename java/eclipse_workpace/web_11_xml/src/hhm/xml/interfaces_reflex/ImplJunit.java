package hhm.xml.interfaces_reflex;

import org.junit.Test;

public class ImplJunit {
	@Test
	public void test(){
		MyServletImpl impl = new MyServletImpl();
		impl.init();
		impl.service();
		impl.destory();
	}
	
	@Test
	public void testReflex1(){
		try {
			String className = "hhm.xml.interfaces_reflex.MyServletImpl";
			Class clazz = Class.forName(className);
			MyServletImpl impl = (MyServletImpl) clazz.newInstance();
			impl.init();
			impl.service();
			impl.destory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
