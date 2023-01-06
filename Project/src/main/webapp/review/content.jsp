<%@page import="java.text.SimpleDateFormat"%>
<%@page import="review.ReviewDAO"%>
<%@page import="review.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">

<title>review/content.jsp</title>

</head>
<body>
<%
int review_num=Integer.parseInt(request.getParameter("review_num"));
System.out.println(review_num);

ReviewDAO dao=new ReviewDAO();

//조회수 증가
dao.updatetReadcount(review_num);

//BoardDTO 리턴할 형 getboard(int num)메서드 정의
ReviewDTO dto=dao.getReivew(review_num);

SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd");
%>
<div class="wrap">
<h1>content</h1>
<table border="1">
<%-- <tr><td>글번호</td><td><%=dto.getReview_num() %></td></tr> --%>
<%-- <tr><td>회원ID</td><td><%=dto.getUser_id() %></td></tr> --%>
<tr><td>차종류</td><td><%=dto.getCar_num() %></td></tr>
<tr><td>별점</td><td><%=dto.getReview_star() %></td></tr>
<%-- <tr><td>작성일자</td><td><%=dto.getReview_date() %></td></tr> --%>
<tr><td>작성일자</td><td><%=dateFormat.format(dto.getReview_date()) %></td></tr>
<tr><td>리뷰</td><td><%=dto.getReview_cont() %></td></tr>



<tr><td colspan="2">
<%
//세션값 가져오기
String id=(String)session.getAttribute("id");

//글쓴이와 로그인(세션값) 일치하면 => 글수정, 글삭제 버튼 보이기
if(dto.getUser_id().equals(id)) {
	%>
	
	<input type="button" value="수정" onclick="location.href='updateForm.jsp?review_num=<%=dto.getReview_num() %>'">
	<input type="button" value="삭제" onclick="location.href='delete.jsp?review_num=<%=dto.getReview_num() %>'">
	
	<%
}
%>

	<input type="button" value="글목록" onclick="location.href='list.jsp'">
	<input type="hidden" name="user_id" value="<%=id %>" readonly>
	<input type="hidden" name="review_num"><br></td></tr>
</table>
</div>
</body>
</html>