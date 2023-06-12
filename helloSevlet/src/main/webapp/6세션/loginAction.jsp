<%@page import="dto.Member"%>
<%@page import="dao.MemberDao"%>
<%@page import="utils.CookieManager"%>
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
	String id = request.getParameter("userid");
	String pw = request.getParameter("userpw");
	
	// 아이디 저장 체크박스
	String saveCheck = request.getParameter("save_check");
	
	if(saveCheck != null && "Y".equals(saveCheck)){
		
		CookieManager.makeCookie(response, "userId", id, 3600);
		
	}
	/*
	MemberDao.login() :
		아이디 비밀번호가 일치하는 사원이 있으면 Member객체를 반환 하고
		없으면 null을 반환
	*/
	
	MemberDao dao = new MemberDao();
	Member member = dao.login(id, pw);
	
	
	if(member != null && !"".equals(member.getName())){
		// 로그인 성공		
		// 세션에 저장
		session.setAttribute("id",id);
	
		response.sendRedirect("login.jsp?name="+id);
	} else {
		// 로그인 실패
		response.sendRedirect("login.jsp?loginErr=Y");
	}
%>
</body>
</html>