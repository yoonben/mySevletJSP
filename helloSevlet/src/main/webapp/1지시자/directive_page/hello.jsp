<!-- 

jsp : 동적인 페이지

지시어(Directive)
jsp페이지를 servlet코드로 반환 하는데 필요한 정보

지시어의 종류
	page 	: JSP페이지에 대한 정보를 설정
	include : 외부파일을 현재 JSP페이지에 포함시킴
	taglib 	: 표현언어에서 사용할 자바 클래스나 JSTL을 선언
	
작성방법
	< %@ 지시어종류 속성1="값1",... (속성 나열) %>

JSP 파일 생성시 기본 인코딩을 변경하는 방법
	window > preferences > JSP Files 선택> encoding utf - 8 선택
-->
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello</h1>
</body>
</html>