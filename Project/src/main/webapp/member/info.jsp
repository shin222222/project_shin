<%@page import="com.itwillbs.member.db.MemberDTO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/info.jsp</title>
</head>
<body>
<h1>나의 정보 조회</h1>
<%
MemberDTO dto=(MemberDTO)request.getAttribute("dto");
	%>
아이디 : <%=dto.getId() %><br>
비밀번호 : <%=dto.getPass() %><br>
이름 : <%=dto.getName() %><br>
가입날짜 : <%=dto.getDate() %><br>
</body>
</html>