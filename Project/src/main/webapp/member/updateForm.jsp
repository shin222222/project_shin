<%@page import="com.itwillbs.member.db.MemberDTO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateForm.jsp</title>
</head>
<body>
<h1>나의 정보 수정</h1>
<%
MemberDTO dto=(MemberDTO)request.getAttribute("dto");
	%>
<form action="./MemberUpdatePro.me" method="get">
아이디	: <input type="text" name="id" value="<%=dto.getId() %>" readonly><br>
비밀번호	: <input type="password" name="pass"><br>
이름		: <input type="text" name="name" value="<%=dto.getName() %>"><br>
<input type="submit" value="회원정보수정">
</form>
<a href="./MemberMain.me">"MemberMain.me" 이동</a>
</body>
</html>