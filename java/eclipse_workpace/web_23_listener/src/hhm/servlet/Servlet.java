package hhm.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hhm.domain.People;
import hhm.domain.Person;

public class Servlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		context.setAttribute("name", "tom");
		context.setAttribute("name", "jerry");
		context.setAttribute("age", 2);
		context.removeAttribute("name");
		
		HttpSession session = request.getSession();
		
		People people = new People();
		people.setAge(21);
		people.setName("jerry");
		session.setAttribute("jerry", people);
		
		
		Person person = new Person();
		person.setAge(12);
		person.setName("tom");
		session.setAttribute("person", person);
		
		session.removeAttribute("person");
		
		Object object = session.getAttribute("jerry");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}