
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
<jsp:include page="Link.jsp" />
<h2>회원제 게시판 - 수정하기(Edit)</h2>
<%
	String num = request.getParameter("num");

	BoardDao dao = new BoardDao();
	Board board = dao.seletOne(num);
	
%>
<form action="EditProcess.jsp?num=<%=num%>" method="post">
	<table border="1" width="90%">
        <tr>
            <td>제목</td>
            <td>
            	<input type="text" name="num" value=<%=request.getParameter("num")%> style="display: none;">
                <input type="text" name="title" value="<%= board.getTitle() %>" style="width: 90%;" />
            </td>
        </tr>
        <tr>
            <td>내용</td>
            <td>
                <textarea name="content" style="width: 90%; height: 100px;"><%= board.getContent() %></textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button type="submit">작성 완료</button>
                <button type="reset">다시 입력</button>
                <button type="button" onclick="location.href='List.jsp';">
                    목록 보기</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>