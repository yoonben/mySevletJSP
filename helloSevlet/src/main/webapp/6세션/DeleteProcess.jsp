<%@page import="dao.BoardDao"%>
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
	String num = request.getParameter("num");
	
	BoardDao dao = new BoardDao();
	
	int res = dao.Delete(num);
	
	if (res > 0) {
		JSFunction.alertLocation("게시글이 삭제되었습니다.", "List.jsp", out);
	} else {
		JSFunction.alertBack("게시글 수정중 오류가 발생하였습니다.", out);
	}
	%>
</body>
</html>