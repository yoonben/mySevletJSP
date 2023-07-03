<%@page import="common.JSFunction"%>
<%@page import="dao.BoardDao"%>
<%@page import="dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="IsLogin.jsp" %>
<%
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String num = request.getParameter("num");
	
	String id = (String)(request.getAttribute("UserId"));
	
	Board board = new Board(num,title,content, id,"","");
	BoardDao dao = new BoardDao();
	
	int res = dao.Edit(board);
	
	if(res>0){
		JSFunction.alertLocation("수정되었습니다", "View.jsp?num="+num, out);
	}else{
		JSFunction.alertBack("수정 작업중 오류가 발생하였습니다.", out);
	}
	
%>
</body>
</html>