<%@page import="dto.PageDto"%>
<%@page import="dto.Criteria"%>
<%@page import="java.util.List"%>
<%@page import="dto.Board"%>
<%@page import="dao.NewBoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<%@ include file="/6세션/Link.jsp" %>
	
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
		<!-- EL에서 사용하기 위해 변수 페이지영역에 저장 -->
		<c:set var="list" value="<%=boardList%>" />
		
        <tr align="center">
		<!-- 리스트가 비었는지 확인 -->
        <c:if test="${empty list}" var="res">
		<tr>
			<td colspan="5" align="center">등록된 게시물이 없습니다.</td>
		</tr>
        </c:if>
        
        <!-- 리스트가 비어 있지 않다면, 리스트를 출력 -->
		<c:if test="${not res}">
			<!-- 반복문을 통해 리스트에 담긴 board객체를 출력
				items : 향상된 for문 -->
			<c:forEach items ="${list}" var="board">
 				<tr align="center">
            		<td>${board.num}</td>
           			<td align="left"><a href="View.jsp?num=${board.num}">${board.title}</a></td>
            		<td align="center">${board.id}</td>
	            	<td align="center">${board.visitcount}</td>
	            	<td align="center">${board.postdate}</td>
        		</tr>
        	</c:forEach>
		</c:if>
    </table>
    
    <!--목록 하단의 [글쓰기] 버튼-->
	<c:if test="${not empty sessionScope.UserId}">
		 <table border="1" width="90%">
        <tr align="right">
            <td><button type="button"
            	onclick="location.href='Write.jsp'">글쓰기</button></td>
        </tr>
    </table>
	</c:if>

    <%
    	PageDto pageDto = new PageDto(totalCnt,criteria);
    %>
    
    <table border="1" width = "90%">
	<tr>
		<td align="center">
			<%@ include file="/6세션/PageNavi.jsp" %>
		</td>
	</tr>
</table>
</body>
</html>
