<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	this is include1 page
	<!-- 包含include2    动态包含  先加载自己的页面，再去加载包含的页面 -->
	<jsp:include page="/include2.jsp"></jsp:include>
	<!-- 包含include2    静态包含  先把页面内容完全引入 再编译运行-->
	<%@ include file="/include2.jsp" %>
</body>
</html>