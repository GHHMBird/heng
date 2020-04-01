package hhm.domain;

import java.io.Serializable;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class Person implements Serializable,HttpSessionBindingListener{
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	private String name;
	private int age;
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		//绑定的方法    当对象被放到session中时相应
		System.out.println("Person放入session");
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		//解绑的方法     当对象从session中移除时相应
		System.out.println("Person移除session");
	}
}
