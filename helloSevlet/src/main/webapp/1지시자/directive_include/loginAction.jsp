<%@page import="utils.CookieManager"%>
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
	String id = request.getParameter("userid");
	String pw = request.getParameter("userpw");
	
	// 아이디 저장 체크박스
	String saveCheck = request.getParameter("save_check");
	
	if(saveCheck != null && "Y".equals(saveCheck)){
		
		CookieManager.makeCookie(response, "userId", id, 3600);
		
	}

	if("abc".equalsIgnoreCase(id)
			&& "123".equals(pw)){
		// 로그인 성공		
		// 세션에 저장
		session.setAttribute("id",id);
		//response.sendRedirect("gogreen.jsp?name="+id);
		response.sendRedirect("gogreen.jsp");
	} else {
		// 로그인 실패
		response.sendRedirect("gogreen.jsp?loginErr=Y");
	}
%>
</body>
</html>