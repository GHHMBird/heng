package hhm.target;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.junit.Test;

public class ProxyTest {
	@Test
	public void test() {
		//在运行时  在内存中动态的为target创建一个虚拟的代理对象
		TargetInterface objProxy = (TargetInterface) Proxy.newProxyInstance(Target.class.getClassLoader(),new Class[] {TargetInterface.class} , new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//method  目标对象的对应方法
				Object invoke = method.invoke(new Target(), args);
				return invoke;
			}
		});
		objProxy.method1();
	}
}
