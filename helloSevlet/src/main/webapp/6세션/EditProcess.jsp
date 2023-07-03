<%@page import="common.JSFunction"%>
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
	<% 
		request.setCharacterEncoding("utf-8");
	
		String num = request.getParameter("num");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Board board = new Board(num, title, content, "","","");
		
		BoardDao dao = new BoardDao();
		int res = dao.Edit(board);
		
		
		
		if(res > 0){
			JSFunction.alertLocation("게시글이 수정되었습니다.", "Edit.jsp?num="+num, out);
		}else{
			JSFunction.alertBack("게시글 수정중 오류가 발생하였습니다.", out);
		}
	%>
</body>
</html>