<%@page import="dto.Criteria"%>
<%@page import="java.util.List"%>
<%@page import="dto.Board"%>
<%@page import="dao.NewBoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원제 게시판</title>
</head>
<body>
	<%
		String searchField = request.getParameter("searchField");
		String searchWord = request.getParameter("searchWord");
		String pageNo = request.getParameter("pageNo");
		
		Criteria criteria = new Criteria(searchField, searchWord, pageNo);
		
		NewBoardDao dao = new NewBoardDao();
		//List<Board> boardList = dao.getList(criteria);
		List<Board> boardList = dao.getListPage(criteria);
		
		// 총 건수 조회
		int totalCnt = dao.getTotalCnt(criteria);
	%>
	<%@ include file="../6세션/Link.jsp" %>
	
	<h1>NEW</h1>
    <h2>목록 보기(List)</h2>
    <!-- 검색폼 --> 
    
    총건수 : <%=totalCnt%>
    
    <form name='seadrchForm'>
		<input type='text' name='pageNo' value='<%=criteria.getPageNo()%>'>
		<table border="1" width = "90%">
			<tr>
				<td>
				<select name="searchField">
						<option value="title">제목</option>
						<option value="content">내용</option>
				</select> 
				<input type="text" name="searchWord" value= <%=criteria.getSearchWord()%>> 
				<input type="submit" value="검색하기">
				</td>
			</tr>
		</table>
	</form>
    <!-- 게시물 목록 테이블(표) --> 
    <table border="1" width="90%">
        <!-- 각 칼럼의 이름 --> 
        <tr>
            <th width="10%">번호</th>
            <th width="50%">제목</th>
            <th width="15%">작성자</th>
            <th width="10%">조회수</th>
            <th width="15%">작성일</th>
        </tr>
        <!-- 목록의 내용 --> 

        <tr align="center">
			<%
		if (boardList.isEmpty()) {
			//게시글이 하나도 없을때
		%>
		<tr>
			<td colspan="5" align="center">등록된 게시물이 없습니다.</td>
		</tr>
		<%
		} else {
 			for(Board board : boardList){
 				%>
 				<tr align="center">
            		<td><%=board.getNum()%></td>
           			<td align="left"><a href="View.jsp?num=<%=board.getNum()%>&pageNo=<%=criteria.getPageNo()%>"><%=board.getTitle()%></a></td>
            		<td align="center"><%=board.getId()%></td>
	            	<td align="center"><%=board.getVisitcount()%></td>
	            	<td align="center"><%=board.getPostdate()%></td>
        		</tr>
 				<%
 			}
 			}	
            %>

    </table>
    <!--목록 하단의 [글쓰기] 버튼-->
    <%if(session.getAttribute("UserId") != null
    		&& !"".equals(session.getAttribute("UserId"))) {%>
    <table border="1" width="90%">
        <tr align="right">
            <td><button type="button"
            	onclick="location.href='Write.jsp'">글쓰기</button></td>
        </tr>
    </table>
    <%}%>
    <%
    	PageDto pageDto = new PageDto(totalCnt,criteria);
    %>
    
    <table border="1" width = "90%">
	<tr>
		<td align="center">
			<%@ include file="../6세션/PageNavi.jsp" %>
		</td>
	</tr>
</table>
</body>
</html>
