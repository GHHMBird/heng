package hhm.domain;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

public class People implements Serializable,HttpSessionActivationListener{
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
	public void sessionDidActivate(HttpSessionEvent arg0) {
		// 活化   服务器正常启动  当前对象从磁盘移到session中
		System.out.println("当前对象从磁盘移到session中");
	}
	@Override
	public void sessionWillPassivate(HttpSessionEvent arg0) {
		// 钝化   服务器正常关闭  当前对象从session移除，存到磁盘上
		System.out.println("当前对象从session移除，存到磁盘上");
	}
}
