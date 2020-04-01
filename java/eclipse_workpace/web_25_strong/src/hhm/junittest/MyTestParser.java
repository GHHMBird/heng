package hhm.junittest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyTestParser {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Class clazz = TestDemo.class;
		Method[] methods = clazz.getMethods();
		if (methods!=null) {
			for (Method method : methods) {
				if (method.isAnnotationPresent(MyTest.class)) {
					method.invoke(clazz.newInstance(), null);
				}
			}
		}
	}
}
