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
	BoardDao dao = new BoardDao();
	
	dao.updateVisitCount(request.getParameter("num"));
	// request.getParameter("num") : 조회하려는 게시글 번호
	// 게시글 1건을 조회 후 board객체에 담아서 반환
	Board board = dao.seletOne(request.getParameter("num"));
	
	if (board != null) {
	%>
	
	<script>
	function deletePost() {
		var res = confirm("삭제 하시겠습니까?");
		if(res){
			location.href="DeleteProcess.jsp?num=<%=board.getNum()%>"
		}
	}
</script>
<jsp:include page="Link.jsp" />
	<h2>회원제 게시판 상세보기</h2>
	<table border="1" width="90%">
        <tr>
            <td>번호</td>
            <td><%= board.getNum() %></td>
            <td>작성자</td>
            <td><%= board.getId() %></td>
        </tr>
        <tr>
            <td>작성일</td>
            <td><%= board.getPostdate() %></td>
            <td>조회수</td>
            <td><%= board.getVisitcount() %></td>
        </tr>
        <tr>
            <td>제목</td>
            <td colspan="3"><%= board.getTitle() %></td>
        </tr>
        <tr>
            <td>내용</td>
            <td colspan="3" height="100">
                <%= board.getContent().replace("\r\n", "<br/>") %></td> 
        </tr>
        <tr>
            <td colspan="4" align="center">
            
                <button type="button" onclick="location.href='List.jsp';">
                    목록 보기
                </button>
                
                <%
                // 로그인한 아이디와 글쓴이가 같으면 수정,삭제 버튼 활성화
                if(session.getAttribute("UserId") != null
                	&& board.getId().equals(session.getAttribute("UserId"))){
                %>
    <button type="button" 
    	onclick="location.href='Edit.jsp?num=<%=board.getNum()%>'">수정하기</button>
    <button type="button" 
    	onclick="deletePost()">삭제하기</button>
                
                <%} %>
                
            </td>
        </tr>
		<%
		} else {
			
		JSFunction.alertBack("게시글이 존재하지 않습니다.", out);
		
		return;
		
		}
		%>
	</table>
</body>
</html>