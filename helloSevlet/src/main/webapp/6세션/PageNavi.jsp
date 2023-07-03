<%@page import="dto.Criteria"%>
<%@page import="dto.PageDto"%>
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
<script>
	function go(page) {
		document.seadrchForm.pageNo.value = page;
		document.seadrchForm.submit();
	}
</script>

<!-- 영역에 저장 -->
<c:set var="pageDto" value="<%=pageDto%>"/>


<c:if test="${pageDto.prev}">
	<input type='button' value = '<<' onclick='go(1)'>
</c:if>

<%	

	if(pageDto.isPrev()){
		// 처음 페이지 이동 블럭
		out.print("<input type='button' value = '<<' onclick='go(1)'>");
	}
	if(pageDto.isPrev()){
		// 이전 페이지 블럭
		out.print("<input type='button' value = '<' onclick='go("+(pageDto.getStartNo()-1)+")'>");
	}
	for(int i = pageDto.getStartNo(); i<= pageDto.getEndNo(); i++){
		out.print("<input type='button' value = "+i+" onclick='go("+i+")'>");
	}
	if(pageDto.isNext()){
		// 이후 페이지 블럭
		out.print("<input type='button' value = '>' onclick='go("+(pageDto.getEndNo()+1)+")'>");
	}
	if(pageDto.isNext()){
		// 마지막 페이지 이동 블럭
		out.print("<input type='button' value = '>>' onclick='go("+(pageDto.getRealEnd())+")'>");
	}
%>
</body>
</html>