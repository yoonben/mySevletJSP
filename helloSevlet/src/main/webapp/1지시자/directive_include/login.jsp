<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/gogreen.css">
</head>
<body>
	<aside id='rightside'>
				<div class='side1'>
					<!-- 로그인 실패 : 메세지 처리 -->
					<!-- 로그인 성공 : 로그인 박스를 보여주지 않음
	                				abc님 환영합니다, 출력 -->

					<%
					// 오류가 발생 한경우
					// 메세지 출력
					String loginErr = request.getParameter("loginErr");

					if ("Y".equals(loginErr)) {
						out.print("<script>alert('아이디 비밀번호를 확인해주세요!')</script>");
					%>
					<%}%>
					<!-- 로그인 :
							method를 post로 설정해서 아이디/비밀번호가 노출되는걸 막아줍니다. -->
					<%
					String name = request.getParameter("name");
					if (name != null && !name.equals("")) {
						out.print(name + "님 환영합니다.");
					} else {
					%>
					<form action="loginAction.jsp">
						<div class='loginbox'>
							<div id='login'>
								<input type="text" name="userid" id="userpw"
									placeholder='ID를 입력해주세요.'> <input type="password"
									name="userpw" id="userpw" placeholder='PW를 입력해주세요.'>
							</div>
							<div id='button'>
								<input type="submit" value="로그인">
							</div>
						</div>
						<div id='info'>
							<a href="">회원가입</a> <a href="">ID찾기</a> <a href="">PW찾기</a>
						</div>
					</form>
					<%}%>
				</div>
				</aside>
</body>
</html>