<%@page import="dto.PageDto"%>
<%@page import="dto.Criteria"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
	function go(page) {
		location.href="PageNavi.jsp?pageNo="+page;
	}
</script>

<%	
	int pageNo = request.getParameter("pageNo") == null ? 1 :Integer.parseInt(request.getParameter("pageNo"));

	Criteria creiteria = new Criteria(pageNo);
	int total = 325;
	PageDto pageDto =new PageDto(total,creiteria);
%>
</body>
</html>