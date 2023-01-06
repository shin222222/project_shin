<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@page import="com.itwillbs.board.db.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>content.jsp</title>
</head>
<body>

<%
BoardDTO dto=(BoardDTO)request.getAttribute("dto");
%>
<table border="1">
<tr><td>글번호</td><td><%=dto.getNum() %></td></tr>
<tr><td>작성자</td><td><%=dto.getName() %></td></tr>
<tr><td>등록일</td><td><%=dto.getDate() %></td></tr>
<tr><td>조회수</td><td><%=dto.getReadcount() %></td></tr>
<tr><td>제목</td><td><%=dto.getSubject() %></td></tr>
<tr><td>글내용</td><td><%=dto.getContent() %></td></tr>
<tr><td colspan="2">
<%
// 세션값 가져오기
String id=(String)session.getAttribute("id");
// 글쓴이와 로그인(세션값) 일치하면 => 글수정, 글삭제 버튼 보이기
if(dto.getName().equals(id) || id.equals("admin")){
	%>
	<input type="button" value="글수정" 
	onclick="location.href='./BoardUpdateForm.bo?num=<%=dto.getNum()%>'">
	<input type="button" value="글삭제" 
	onclick="location.href='./BoardDelete.bo?num=<%=dto.getNum()%>'">
	<%
}
%>
<input type="button" value="글목록" 
onclick="location.href='./BoardList.bo'">
</td></tr>
</table>
</body>
</html>