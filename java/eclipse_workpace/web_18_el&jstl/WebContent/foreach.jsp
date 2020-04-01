<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="java.util.*" %>
    <%@ page import="hhm.bean.User" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		for(int i = 0 ; i < 10 ; i ++){
			System.out.print(i);
		}
	%>
	
	<c:forEach begin="0" end="9" var="i" step="1" >
		${i}<br/>
	</c:forEach>
	
	<hr>
	
	<%
		ArrayList<User> list = new ArrayList();
		for(int i = 0 ; i < 10 ; i ++){
			User u = new User();
			u.setAge(i);
			list.add(u);
		}
		request.setAttribute("arrayList", list);
		for(User u : list){
			System.out.print(u.getAge());
		}
	%>
	
	<!-- items代表一个集合或者数组     var代表元素 -->
	<c:forEach items="${arrayList}" var="u">
		${u.age}
	</c:forEach>
	
	<hr>
	
	<%
		Map<String ,String> strMap = new HashMap();
		strMap.put("name", "zhangsan");
		strMap.put("age", "18");
		strMap.put("address", "where");
		session.setAttribute("strMap", strMap);
	%>
	
	<c:forEach items="${strMap}" var="entry">
		${entry.key}:${entry.value}    <br/>
	</c:forEach>
	
	<hr>
	
	<%
		Map<String ,User> beanMap = new HashMap();
		for(int i = 0 ; i < 10 ; i ++){
			User u = new User();
			u.setName("user"+i);
			beanMap.put("user"+i, u);
		}
		session.setAttribute("beanMap", beanMap);
	%>
	
	<c:forEach items="${beanMap}" var="item" >
		${item.key}:${item.value.name}   <br/>
	</c:forEach>
	
</body>
</html>