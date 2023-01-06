<%@page import="com.itwillbs.qna.db.QnaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateForm.jsp</title>
</head>
<body>
<%
QnaDTO dto=(QnaDTO)request.getAttribute("dto");
String id=(String)session.getAttribute("id");
	if(id==null || !id.equals(dto.getUser_id())){
		response.sendRedirect("./BoardList.qn");
	}
%>
<h1>글수정</h1>
<form action="./QnaUpdatePro.qn" method="post">
<input type="hidden" name="qna_num" value="<%=dto.getQna_num()%>">
<table border="1">
<tr><td>글쓴이</td>
<td><input type="text" name="user_id" value="<%=dto.getUser_id() %>" readonly></td></tr>
<tr><td>제목</td>
<td><input type="text" name="qna_sub" value="<%=dto.getQna_sub() %>" maxlength="50"></td></tr>
<tr><td>글내용</td>
<td><textarea name="qna_content" rows="10" cols="20" maxlength="1500"><%=dto.getQna_content()%></textarea></td></tr>
<tr><td colspan="2"><input type="submit" value="글쓰기"></td><td></td></tr>
</table>
</form>
</body>
</html>