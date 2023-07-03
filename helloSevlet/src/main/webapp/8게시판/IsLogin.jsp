<%@page import="common.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	if(session.getAttribute("UserId") == null){
		JSFunction.alertLocation("로그인후 이용이 가능합니다.", "../6세션/LoginForm.jsp", out);
	}
%>
</body>
</html>