<%@page import="java.util.Set"%>
<%@page import="dto.Person"%>
<%@page import="java.util.Map"%>
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
	// 브라우저를 닫았다가 다시 열어도 값이 유지가 됩니다.
	// 서버를 종료후 다시 실행시 제거되는것을 확인 할 수 있습니다.
	Map<String, Person> maps
	= (Map<String, Person>)application.getAttribute("maps");
	
	// 맵에 담겨진 데이터의 키값을 조회 합니다.
	// keySet() : 키의집합을 set타입으로 변환 합니다.
	Set<String> keys = maps.keySet();
	
	for(String key : keys){
		Person p = maps.get(key);
		out.print(p.getName() + "/ "+ p.getAge() +"<br>");
	}
%>
</body>
</html>