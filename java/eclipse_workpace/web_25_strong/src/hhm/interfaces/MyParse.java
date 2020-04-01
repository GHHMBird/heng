package hhm.interfaces;

import java.lang.reflect.Method;

import hhm.classloader.TestClassLoader;

public class MyParse {
	public static void main(String[] args) {
		
		//注解的解析器
		Class clazz = TestClassLoader.class;
		try {
			Method metohd = clazz.getMethod("test", String.class);
			My_at at = metohd.getAnnotation(My_at.class);
			System.out.println("age="+at.age()+";name="+at.name()+";id="+at.id());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Deprecated
	public void outTime() {
		//过时注解
	}
}
