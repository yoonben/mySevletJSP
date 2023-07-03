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
	<h4>JSTL의 if 태그로 짝수/홀수 판단하기</h4>
	<c:set var="number" value="101"/>
	<!-- 
		test	: if문에서 사용할 조건 지정(표현언어 사용 가능)
		vae		: 조건의 결과를 저장할 변수명
		scope	: qustnrk wjwkdehlf 영역 
	 -->
	 <!-- number 를 2로 나눈 나머지가 0과 같다 -->
	 <c:if test="${number mod 2 eq 0}" var = "result">
	 	<!-- 조건이 참인 경우 실행됩니다. -->
	 	${number }는 짝수입니다.<br>
	 </c:if>
	 
	<c:if test="${not result}">
		${number }는 홀수입니다.<br>
	</c:if>
	
		result : ${result}<br>
		
	<h4>문자열과 비교와 else 구문 흉내내기</h4>
	<!-- 문자열 변수 선언 -->
	<c:set var="str" value="JSP"/>
	<c:if test="${str eq 'java' }" var="result3">
		문자열은 Java입니다.<br>
	</c:if>
	
	<c:if test="${not result3 }" var="result3">
		문자열은 Java가 아닙니다.<br>
	</c:if>
	
	<h4>조건식 주의사항</h4>
	<!-- 표현언어(EL)를 상ㅇ하지 않은 경우 -->
	<c:if test="100" var="result4">
		일반 값을 입력 : false<br>
	</c:if>
	result4 : ${result4 }<br>
	
	<c:if test="tRuE" var="result5">
		대소문자 구분 없이 "tRuE"인 경우<br>
	</c:if>
	result5 : ${result5 }<br>
	
	<c:if test="${true } " var="result6">
		EL 공뱍이 삽입된 경우 false<br>
	</c:if>
	result6 : ${result6 }<br>
</body>
</html>