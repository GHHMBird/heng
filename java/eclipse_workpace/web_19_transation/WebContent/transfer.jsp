<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/transfer" method="post">
		<p>转出账户:<input type="text" name="out"></p>
		<p>转入账户:<input type="text" name="in"></p>
		<p>转账金额:<input type="text" name="money"></p>
		<input type="submit" value="确认转账"> 
	</form>
</body>
</html>