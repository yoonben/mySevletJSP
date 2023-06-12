<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
	cookie
	요청시 요청헤더에 쿠키정보를 포함하여 요청!
	클라이언트의 상태 정보를 클라이언트 pc에 저장합니다
	로그인아이디를 저장하거나, 팝업창을 제어하는데 주로 사용 됩니다.
	
	- 3000개까지 생성 가능합니다
	- 하나의 호스트 또는 도메인당 5개 까지 생성 가능 합니다
	- 쿠키 하나당 최대 크기는 4096byte입니다.
	
	쿠키의 속성
	
	도메인 : 쿠키를 적용할 도메인
	경로 : 쿠키를 적용할 경로
	유지시간 : 쿠키를 유지할 시간
 -->
 
 <h1>1. 쿠키 설정</h1>
 <%
 	/*
 	쿠키 생성
 	new Cookie(이름, 값);
 		이름 : 쿠키를 구별하는 이름
 		값  : 쿠키에 저장할 실제 데이터
 	*/
 	
 	Cookie cookie = new Cookie("myCookie",URLEncoder.encode("쿠키맛나요","utf-8"));
 		
 		
 	// 쿠키가 적요될 경로를 지정(하위경로까지 사용가능)
 	// request.getContextPath() : request객체로 부터 컨텍스트 루트 경로 조회
 	cookie.setPath(request.getContextPath());
 	
 	// 쿠키가 유지될 기간을 초단위로 지정합니다.(3600=1시간)
 	cookie.setMaxAge(3600);
 	
 	// 생성한 쿠키를 응답객체에 추가
 	response.addCookie(cookie);
 %>
 	
 	<h2>3. 페이지 이동후 쿠키 값 확인하기</h2>
 	<a href="CoockeResult.jsp">쿠기값 확인하기</a>
 	
</body>
</html>