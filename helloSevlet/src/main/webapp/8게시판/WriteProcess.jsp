<%@page import="common.JSFunction"%>
<%@page import="dto.Member"%>
<%@page import="dto.Board"%>
<%@page import="dao.BoardDao"%>
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
	request.setCharacterEncoding("utf-8");

	Board board = new Board();
	BoardDao dao = new BoardDao();
	
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	// 사용자가 로그아웃 하지 않았더라도
	// 일정시간이 경과되면 세션이 제거 되므로 오류가 발생 할 수 있다
	String id = (String)session.getAttribute("UserId") == null ? "" : (String)session.getAttribute("UserId");
	
	board.setTitle(title);
	board.setContent(content);
	board.setId(id);
	
	int res = dao.insert(board);
	
	
	if(res > 0){
		

		JSFunction.alertLocation("게시글이 등록 되었습니다.", "List.jsp", out);

	}else{
		

		JSFunction.alertBack("등록중 오류가 발생 하였습니다.", out);

	}
%>
</body>
</html>