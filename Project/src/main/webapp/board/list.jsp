<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
</head>
<body>
<h1>글목록</h1>
<%
List<BoardDTO> boardList=(List<BoardDTO>)request.getAttribute("boardList");
// startPage pageBlock currentPage endPage pageCount
int startPage=(Integer)request.getAttribute("startPage");
int pageBlock=(Integer)request.getAttribute("pageBlock");
int currentPage=(Integer)request.getAttribute("currentPage");
int endPage=(Integer)request.getAttribute("endPage");
int pageCount=(Integer)request.getAttribute("pageCount");
%>
	<table border="1">
	<tr><td>번호</td><td>글쓴이</td><td>제목</td>
	<td>등록일</td><td>조회</td></tr>
	<%
for(int i = 0; i<boardList.size(); i++){
	BoardDTO dto = boardList.get(i);
	%>
<tr><td><%=dto.getNum() %></td> 
	<td><%=dto.getName() %></td> 
	<td><a href="BoardContent.bo?num=<%=dto.getNum()%>"><%=dto.getSubject() %></a></td> 
	<td><%=dto.getDate() %></td>
	<td><%=dto.getReadcount() %></td></tr>
<%
}
%>
</table>
<br>
<%
// 10페이지 이전
if(startPage>pageBlock) {
	%>
	<a href="BoardList.bo?pageNum=<%=startPage-pageBlock %>">[10이전]</a>
<%
}
// 1페이지 이전
if(currentPage != 1) {
	%>
<%-- 	<a href="BoardList.bo?pageNum=<%=currentPage-1 %>">[이전]</a> --%>
	<%
}
for(int i=startPage; i<=endPage; i++){
	%>
	<a href="BoardList.bo?pageNum=<%=i%>"><%=i%></a>
	<%
}


// 1페이지 다음
if(currentPage < pageCount) {
	%>
<%-- 	<a href="BoardList.bo?pageNum=<%=currentPage+1 %>">[다음]</a> --%>
	<%
}

//10페이지 다음
if(endPage < pageCount) {
	%>
	<a href="BoardList.bo?pageNum=<%=startPage+pageBlock %>">[10다음]</a>
	<%
}
%>


<!-- <a href="list.jsp?pageNum=1">1</a>  -->
<!-- <a href="list.jsp?pageNum=2">2</a>  -->
<!-- <a href="list.jsp?pageNum=3">3</a>  -->
<!-- <a href="list.jsp?pageNum=4">4</a>  -->
<!-- <a href="list.jsp?pageNum=5">5</a> <br> -->
<br>
<a href="./MemberMain.me">메인으로 이동</a><br>
<a href="BoardWriteForm.bo">글쓰기</a><br>
</body>
</html>