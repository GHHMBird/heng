package hhm.classloader;

import java.net.URL;

import hhm.interfaces.My_at;
import hhm.interfaces.My_value;
import hhm.interfaces.My_value_list;

public class TestClassLoader {
	
	@My_value_list({ "hhm" , "tom" , "jerry" })
	@My_value("hhm")
	@My_at(/*age = 0*/id=0, name = "hhm")
	public static void main(String[] args) {
		ClassLoader loader = TestClassLoader.class.getClassLoader();
		URL path = loader.getResource("jdbc.properties");//可以获得src下面的任何资源
		System.out.println(path.toString()+"\r\n"+path.getPath());
	}
	
	@My_value_list({ "hhm" , "tom" , "jerry" })
	@My_value("hhm")
	@My_at(/*age = 0*/id=0, name = "hhm")
	public void test(String str) {
		
	}
	
}