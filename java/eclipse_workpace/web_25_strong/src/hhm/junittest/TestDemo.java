package hhm.junittest;

import org.junit.Test;

public class TestDemo {
	@Test
	public void test() {
		System.out.println("testDemo");
	}
	
	@MyTest
	public void myTest() {
		System.out.println("myTestDemo");
	}
}
