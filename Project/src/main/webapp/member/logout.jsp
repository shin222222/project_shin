<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/logout.jsp</title>
</head>
<body>
<%
//세션값 초기화(세션내장객체 전체 삭제)
session.invalidate(); //(브라우저안에 탭하나를 로그아웃하면 다 로그아웃됨)
%>
<script type="text/javascript">
	alert("로그아웃");
//	location.href="loginForm.jsp";
	location.href="main.jsp";
	

</script>
</body>
</html>