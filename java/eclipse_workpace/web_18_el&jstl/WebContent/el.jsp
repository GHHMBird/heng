<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="hhm.bean.User" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 模拟域中的数据 -->
	<%
		request.setAttribute("company", "haili");
	
		session.setAttribute("company", "wuwa");
	
		session.setAttribute("name", "zhangsan");
		
		User user = new User();
		user.setAge(21);
		user.setName("lisi");
		user.setSex("male");
		
		session.setAttribute("user", user);
		
		ArrayList<User> list = new ArrayList();
		
		for(int i = 0 ; i < 10 ; i++ ){
			User u = new User();
			u.setName("u"+i);
			list.add(u);
		}
		
		application.setAttribute("list", list);
	%>
	
	
	
	
	
	<hr>
	数据准备完毕，用jsp脚本在下方输出
	<hr>
	<%=request.getAttribute("company")	%><br/>
	<%=((User)session.getAttribute("user")).getName() %>
	
	<br/><br/><br/>
	<hr>
	数据准备完毕，用EL表达式在下方输出
	<hr>
	${requestScope.company}<br/>
	${sessionScope.name }<br/>
	${sessionScope.user.name }<br/>
	${applicationScope.list[1].name }
	
	<br/><br/><br/>
	<hr>
	使用EL全域查找
	<hr>
	${company }<br/>
	${name }<br/>
	${user.name }<br/>
	${list[7].name }
	
	<!-- EL的11个内置对象 -->
	<!-- pageScope requestScope applicationScope  sessionScope -->
	<!-- param/paramValues相当于request.getParameter/request.getParameterValues -->
	<!-- header/headerValues相当于request.getHeader -->
	<!-- initParam -->
	<!-- cookie -->
	<!-- pageContext -->
	<br/><br/><br/>
	<hr>
	EL内置的对象
	<hr>
	${header.Host }<br/>
	<%
		Cookie cookie = new Cookie("name","hhm");
		response.addCookie(cookie);
	%>
	${cookie.name.value }<br/>
	
	<!-- 通过EL表达式获得request对象和下面的属性(如项目名称)      request对象和requestScope不一样 -->
	${pageContext.request.contextPath }
	<!-- 用法 -->
	<%-- <form action="${pageContext.request.contextPath }/xxx.html"></form> --%>
	
	<br/><br/><br/>
	<hr>
	EL执行表达式运算
	<hr>
	${1+1+1 }<br/>
	${1>0?"true":"false" }<br/>
	<!-- empty 判断某个对象是否是空的 -->
	${empty "" }<br/>
	${empty user }
	
</body>
</html>