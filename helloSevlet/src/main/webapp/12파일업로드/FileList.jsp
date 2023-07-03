
<%@page import="java.net.URLEncoder"%>
<%@page import="fileuoload.FileDto"%>
<%@page import="java.util.List"%>
<%@page import="fileuoload.FileDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>DB에 등록된 파일 목록 보기</h2>
<a href="FileUpload.jsp">파일등록하기</a>

		<c:set var="list" value="${requestScope.list }" />
		
<table border="1" width="90%">
        <!-- 각 칼럼의 이름 --> 
        <tr>
            <th width="5%">No</th>
            <th width="5%">작성자</th>
            <th width="15%">제목</th>
            <th width="10%">카테고리</th>
            <th width="20%">원본파일명</th>
            <th width="20%">저장된파일명</th>
            <th width="10%">작성일</th>
            <th width="10%"></th>
        </tr>
        <!-- 목록의 내용 --> 
		<!-- EL에서 사용하기 위해 변수 페이지영역에 저장 -->
		
        <tr align="center">
        <c:if test="${empty list}" var="res">
		<tr>
			<td colspan="5" align="center">등록된 게시물이 없습니다.</td>
		</tr>
        </c:if>
        
		<c:if test="${not res}">

			<c:forEach items ="${list}" var="file">
 					<tr align="center">
            		<td>${file.idx}</td>
           			<td>${file.name}</td>
            		<td>${file.title}</td>
	            	<td>${file.cate}</td>
	            	<td>${file.ofile}</td>
	            	<td>${file.sfile}</td>
	            	<td>${file.postdate}</td>
	            	<td><a href="Download.jsp?oName=${file.ofile}&sName=${file.sfile}"> 다운로드</a></td>
        		</tr>
        	</c:forEach>
		</c:if>
    </table>


</body>
</html>